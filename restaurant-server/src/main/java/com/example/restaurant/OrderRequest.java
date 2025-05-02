package com.example.restaurant;

public class OrderRequest {
    private int item;
    private int quantity;

    public OrderRequest() {}

    public OrderRequest(int item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public int getItem() { return item; }
    public void setItem(int item) { this.item = item; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}