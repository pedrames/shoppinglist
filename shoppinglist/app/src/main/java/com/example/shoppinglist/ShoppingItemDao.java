package com.example.shoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update; // Add this import

import java.util.List;

@Dao
public interface ShoppingItemDao {
    @Insert
    void insert(ShoppingItem shoppingItem);

    @Delete
    void delete(ShoppingItem shoppingItem);

    @Query("SELECT * FROM shopping_items")
    LiveData<List<ShoppingItem>> getAllItems();

    @Update
    void update(ShoppingItem shoppingItem); // Use @Update annotation for updating
}

