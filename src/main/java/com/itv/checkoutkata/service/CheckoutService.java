package com.itv.checkoutkata.service;

import com.itv.checkoutkata.DTO.CheckoutDTO;
import com.itv.checkoutkata.entities.ItemEntity;
import com.itv.checkoutkata.entities.ItemRuleEntity;
import com.itv.checkoutkata.model.Checkout;
import com.itv.checkoutkata.model.Item;
import com.itv.checkoutkata.model.ItemRule;
import com.itv.checkoutkata.repository.ItemRepository;
import com.itv.checkoutkata.repository.ItemRuleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CheckoutService {
    private ModelMapper modelMapper;
    private ItemRuleRepository itemRuleRepository;
    private ItemRepository itemRepository;

    private Map<String, Item> itemMap;
    private Map<String, Checkout> recentCheckouts;

    @Autowired
    public CheckoutService(ModelMapper modelMapper, ItemRuleRepository itemRuleRepository, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRuleRepository = itemRuleRepository;
        this.itemRepository = itemRepository;

        itemMap = new HashMap<>();
        recentCheckouts = new HashMap<>();
    }

    @PostConstruct
    public void init(){
        this.itemMap = fetchItems();
    }

    public CheckoutDTO scan(String uuid, String itemName) {
        if ("".equals(uuid) || !recentCheckouts.containsKey(uuid)) {
            uuid = UUID.randomUUID().toString();
        }

        final String uuidResult = uuid;

        Checkout checkout = recentCheckouts.computeIfAbsent(uuidResult, (k) -> {
            Checkout c = new Checkout();
            c.setUuid(uuidResult);
            fetchItemRules().forEach(ir -> c.addItemRule(ir));

            return c;
        });

        Item item = itemMap.get(itemName);

        if (item != null) {
            checkout.scan(item);
        }

        return modelMapper.map(checkout, CheckoutDTO.class);
    }

    private List<ItemRule> fetchItemRules(){
        List<ItemRuleEntity> itemRuleEntities = itemRuleRepository.findAll();

        return itemRuleEntities.stream()
                .map(ire -> modelMapper.map(ire, ItemRule.class))
                .collect(Collectors.toList());
    }

    private Map<String, Item> fetchItems(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        return itemEntities.stream()
                .map(ie -> modelMapper.map(ie, Item.class))
                .collect(Collectors.toMap(Item::getName, i -> i));
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, Item> itemMap) {
        this.itemMap = itemMap;
    }

    public Map<String, Checkout> getRecentCheckouts() {
        return recentCheckouts;
    }

    public void setRecentCheckouts(Map<String, Checkout> recentCheckouts) {
        this.recentCheckouts = recentCheckouts;
    }
}
