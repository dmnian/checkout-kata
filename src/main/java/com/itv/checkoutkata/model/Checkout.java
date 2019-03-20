package com.itv.checkoutkata.model;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private List<Item> items;

    public Checkout() {
        items = new ArrayList<>();
    }

    public boolean scan(Item item) {
        if(item != null){
           return items.add(item);
        }

        return false;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
