package com.itv.checkoutkata.model;

public class ItemRule {
    private String itemName;

    private int itemCount;

    private int specialPrice;

    public ItemRule(String itemName, int itemCount, int specialPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.specialPrice = specialPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }
}
