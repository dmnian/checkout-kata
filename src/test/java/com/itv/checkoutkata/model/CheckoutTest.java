package com.itv.checkoutkata.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutTest {

    private Checkout checkout;
    private Item itemA;
    private ItemRule itemRuleA;

    @Before
    public void setUp() throws Exception {
        checkout = new Checkout();

        itemA = new Item("A", 50);
        itemRuleA = new ItemRule("A", 3, 130);
    }

    @Test
    public void scanItemSuccessTest() {
        boolean scan = checkout.scan(itemA);

        assertTrue(scan);
        assertEquals(1, checkout.getItems().size());
    }

    @Test
    public void scanItemFailureTest() {
        boolean scan = checkout.scan(null);

        assertFalse(scan);
        assertTrue(checkout.getItems().isEmpty());
    }

    @Test
    public void cancelItemTest() {
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);

        boolean result = checkout.cancelItem(itemA);

        assertTrue(result);
        assertEquals(2, checkout.getItems().size());
    }

    @Test
    public void addItemRuleSuccessTest() {
        boolean result = checkout.addItemRule(itemRuleA);

        assertTrue(result);
    }

    @Test
    public void addItemRuleFailureTest() {
        boolean result = checkout.addItemRule(null);

        assertFalse(result);
    }

    @Test
    public void calculateItemsWithoutRulesTest() {
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(150, result);
    }

    @Test
    public void calculateItemsWithSingleRuleTest() {
        checkout.addItemRule(itemRuleA);

        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(130, result);
    }
}