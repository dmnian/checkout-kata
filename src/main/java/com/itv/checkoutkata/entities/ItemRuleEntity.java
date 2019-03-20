package com.itv.checkoutkata.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "item_rules")
public class ItemRuleEntity {
    @Id
    private int id;

    private String itemName;

    private int itemCount;

    private int specialPrice;

    public ItemRuleEntity() {
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
