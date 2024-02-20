package com.example.shoppinglist;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {ShoppingItem.class}, version = 2, exportSchema = false)
public abstract class ShoppingItemDatabase extends RoomDatabase {
    public abstract ShoppingItemDao shoppingItemDao();

    private static ShoppingItemDatabase instance;
    public static Executor databaseWriteExecutor = Executors.newFixedThreadPool(4); // Initialize databaseWriteExecutor

    public static synchronized ShoppingItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ShoppingItemDatabase.class, "shopping_item_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
