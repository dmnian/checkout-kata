package com.itv.checkoutkata.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutTest {

    private Checkout checkout;
    private Item itemA;
    private Item itemB;
    private Item itemC;
    private Item itemD;
    private ItemRule itemRuleA;
    private ItemRule itemRuleB;

    @Before
    public void setUp() throws Exception {
        checkout = new Checkout();

        itemA = new Item("A", 50);
        itemB = new Item("B", 30);
        itemC = new Item("C", 20);
        itemD = new Item("D", 15);

        itemRuleA = new ItemRule("A", 3, 130);
        itemRuleB = new ItemRule("B", 2, 45);
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
    public void calculateItemsWithSingleRuleThatExistTest() {
        checkout.addItemRule(itemRuleA);

        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(130, result);
    }

    @Test
    public void calculateItemsWithSingleRuleDifferentRuleExistTest() {
        checkout.addItemRule(itemRuleB);

        checkout.scan(itemA);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(100, result);
    }

    @Test
    public void calculateItemsWithSingleRuleNoPromotionTest() {
        checkout.addItemRule(itemRuleA);

        checkout.scan(itemA);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(100, result);
    }

    @Test
    public void calculateItemsWithManyOrderedRulesTest() {
        checkout.addItemRule(itemRuleA);
        checkout.addItemRule(itemRuleB);

        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemB);
        checkout.scan(itemB);

        int result = checkout.calculate();

        assertEquals(2 * 130 + 45, result);
    }

    @Test
    public void calculateItemsWithManyUnorderedRulesTest() {
        checkout.addItemRule(itemRuleA);
        checkout.addItemRule(itemRuleB);

        checkout.scan(itemA);
        checkout.scan(itemB);
        checkout.scan(itemA);
        checkout.scan(itemB);
        checkout.scan(itemA);

        int result = checkout.calculate();

        assertEquals(130 + 45, result);
    }

    @Test
    public void calculateVariousItemsWithManyRulesUnorderedTest() {
        checkout.addItemRule(itemRuleA);
        checkout.addItemRule(itemRuleB);

        checkout.scan(itemA);
        checkout.scan(itemA);
        checkout.scan(itemB);
        checkout.scan(itemA);
        checkout.scan(itemB);
        checkout.scan(itemC);
        checkout.scan(itemD);

        int result = checkout.calculate();

        assertEquals(130 + 45 + 20 + 15, result);
    }


}