package com.itv.checkoutkata.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "items")
public class ItemEntity {
    @Id
    private int id;

    private String name;

    private int price;

    public ItemEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
