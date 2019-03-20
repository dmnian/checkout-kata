package com.itv.checkoutkata.model;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private List<Item> items;
    private List<ItemRule> itemRules;

    public Checkout() {
        items = new ArrayList<>();
        itemRules = new ArrayList<>();
    }

    public boolean scan(Item item) {
        if (item != null) {
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

    public List<ItemRule> getItemRules() {
        return itemRules;
    }

    public void setItemRules(List<ItemRule> itemRules) {
        this.itemRules = itemRules;
    }

    public boolean cancelItem(Item item) {
        if (item != null) {
            return items.remove(item);
        }
        return false;
    }

    public boolean addItemRule(ItemRule itemRule) {
        if (itemRule != null) {
            return itemRules.add(itemRule);
        }
        return false;
    }
}
