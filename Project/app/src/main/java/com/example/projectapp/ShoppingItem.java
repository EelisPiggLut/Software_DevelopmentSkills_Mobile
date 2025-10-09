package com.example.projectapp;

public class ShoppingItem {
    public String name;
    public String amount;

    public ShoppingItem(String name, String quantity) {
        this.name = name;
        this.amount = quantity;
    }

    @Override
    public String toString() {
        return name + " - Amount: " + amount;
    }
}