package com.itv.checkoutkata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Checkout {
    private List<Item> items;
    private List<ItemRule> itemRules;

    private String uuid;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public int calculate() {
        if (itemRules.isEmpty()) {
            return items.stream()
                    .mapToInt(i -> i.getPrice())
                    .sum();
        }

        AtomicInteger sum = new AtomicInteger(0);

        Map<String, List<Item>> itemGroups = items.stream()
                .collect(Collectors.groupingBy(i -> i.getName()));

        itemGroups.forEach((name, groupItems) -> {
            Optional<ItemRule> itemRuleOptional = itemRules.stream()
                    .filter(r -> r.getItemName().equals(name)).findFirst();

            if (itemRuleOptional.isPresent()) {
                ItemRule itemRule = itemRuleOptional.get();

                int numberOfPromotions = groupItems.size() / itemRule.getItemCount();

                if (numberOfPromotions > 0) {
                    final int specialPrice = itemRule.getSpecialPrice();

                    sum.addAndGet(numberOfPromotions * specialPrice);
                    sum.addAndGet(groupItems.size() % itemRule.getItemCount() * specialPrice);
                } else {
                    sum.addAndGet(groupItems.size() * groupItems.get(0).getPrice());
                }
            } else {
                sum.addAndGet(groupItems.size() * groupItems.get(0).getPrice());
            }
        });

        return sum.get();
    }
}
