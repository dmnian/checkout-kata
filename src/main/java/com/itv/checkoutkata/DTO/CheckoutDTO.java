package com.itv.checkoutkata.DTO;

import com.itv.checkoutkata.model.Item;

import java.util.List;

public class CheckoutDTO {
    private List<Item> items;
    private String uuid;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
