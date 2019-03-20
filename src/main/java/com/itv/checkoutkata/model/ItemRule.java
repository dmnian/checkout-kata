package com.itv.checkoutkata.model;

public class ItemRule {
    private String itemRule;

    private int itemCount;

    private int specialPrice;

    public ItemRule(String itemRule, int itemCount, int specialPrice) {
        this.itemRule = itemRule;
        this.itemCount = itemCount;
        this.specialPrice = specialPrice;
    }

    public String getItemRule() {
        return itemRule;
    }

    public void setItemRule(String itemRule) {
        this.itemRule = itemRule;
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
