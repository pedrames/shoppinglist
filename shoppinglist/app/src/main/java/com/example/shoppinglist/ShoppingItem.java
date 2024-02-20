package com.example.shoppinglist;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_items")
public class ShoppingItem {
    @ColumnInfo(name = "id") // Specify the column name
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    @ColumnInfo(name = "item_description")
    String itemDescription;

    @ColumnInfo(name = "item_quantity")
    int itemQuantity;

    public ShoppingItem(String itemName) {
        this.itemName = itemName;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return 0;
    }

    public void setItemName(String updatedName) {
    }
}
