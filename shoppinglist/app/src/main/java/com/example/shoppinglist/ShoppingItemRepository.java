package com.example.shoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingItemRepository {
    private ShoppingItemDao shoppingItemDao;
    private LiveData<List<ShoppingItem>> allItems;

    public ShoppingItemRepository(Application application) {
        ShoppingItemDatabase database = ShoppingItemDatabase.getInstance(application);
        shoppingItemDao = database.shoppingItemDao();
        allItems = shoppingItemDao.getAllItems();
    }

    public void insert(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(() -> {
            shoppingItemDao.insert(shoppingItem);
        });
    }

    public void delete(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(() -> {
            shoppingItemDao.delete(shoppingItem);
        });
    }

    public void update(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(() -> {
            shoppingItemDao.update(shoppingItem);
        });
    }

    public LiveData<List<ShoppingItem>> getAllItems() {
        return allItems;
    }
}
