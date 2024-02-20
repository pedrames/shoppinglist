package com.example.shoppinglist;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingItemViewModel extends AndroidViewModel {
    private ShoppingItemRepository repository;
    private LiveData<List<ShoppingItem>> allItems;

    public ShoppingItemViewModel(Application application) {
        super(application);
        repository = new ShoppingItemRepository(application);
        allItems = repository.getAllItems();
    }

    LiveData<List<ShoppingItem>> getAllItems() {
        return allItems;
    }

    public void insert(ShoppingItem item) {
        repository.insert(item);
    }

    public void delete(ShoppingItem item) {
        repository.delete(item);
    }

    public void update(ShoppingItem updatedItem) {
        repository.update(updatedItem);

    }
}

