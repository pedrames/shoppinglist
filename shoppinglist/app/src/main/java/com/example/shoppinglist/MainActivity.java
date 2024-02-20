package com.example.shoppinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ShoppingItemViewModel shoppingItemViewModel;
    private ShoppingItemAdapter adapter;
    private ProgressBar progressBar; // Added ProgressBar reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText editTextItem = findViewById(R.id.editTextItem);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        progressBar = findViewById(R.id.progressBar); // Initialize ProgressBar

        adapter = new ShoppingItemAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingItemViewModel = new ViewModelProvider(this).get(ShoppingItemViewModel.class);
        shoppingItemViewModel.getAllItems().observe(this, new Observer<List<ShoppingItem>>() {
            @Override
            public void onChanged(List<ShoppingItem> shoppingItems) {
                adapter.setShoppingItems(shoppingItems);
                hideLoading(); // Hide the loading spinner after updating the list
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading(); // Show the loading spinner before adding an item

                String itemName = editTextItem.getText().toString().trim();
                if (!itemName.isEmpty()) {
                    shoppingItemViewModel.insert(new ShoppingItem(itemName));
                    editTextItem.getText().clear();
                }
            }
        });

        // Handle "Edit" button click
        adapter.setOnEditButtonClickListener(new ShoppingItemAdapter.OnEditButtonClickListener() {
            @Override
            public void onEditButtonClick(ShoppingItem item) {
                showEditItemDialog(item);
            }
        });

        // Handle "Delete" button click
        adapter.setOnDeleteButtonClickListener(new ShoppingItemAdapter.OnDeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(ShoppingItem item) {
                shoppingItemViewModel.delete(item);
            }
        });
    }

    private void showEditItemDialog(final ShoppingItem item) {
        // Existing code for the dialog
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
