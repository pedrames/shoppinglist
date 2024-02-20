package com.example.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemAdapter extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {
    private List<ShoppingItem> items;
    private Context context;
    private OnEditButtonClickListener editButtonClickListener;
    private OnDeleteButtonClickListener deleteButtonClickListener;

    public interface OnEditButtonClickListener {
        void onEditButtonClick(ShoppingItem item);
    }

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(ShoppingItem item);
    }

    public void setOnEditButtonClickListener(OnEditButtonClickListener listener) {
        this.editButtonClickListener = listener;
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener listener) {
        this.deleteButtonClickListener = listener;
    }

    public ShoppingItemAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (items != null && position < items.size()) {
            final ShoppingItem item = items.get(position);
            holder.itemNameTextView.setText(item.getItemName());

            // Handle "Edit" button click
            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editButtonClickListener != null) {
                        editButtonClickListener.onEditButtonClick(item);
                    }
                }
            });

            // Handle "Delete" button click
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteButtonClickListener != null) {
                        deleteButtonClickListener.onDeleteButtonClick(item);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.items = shoppingItems;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        Button editButton;
        Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textItemName);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
