package com.itv.checkoutkata.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutTest {

    private Checkout checkout;

    @Before
    public void setUp() throws Exception {
        checkout = new Checkout();
    }

    @Test
    public void scanItemSuccessTest() {
        boolean scan = checkout.scan(new Item("A", 50));

        assertTrue(scan);
        assertEquals(1, checkout.getItems().size());
    }

    @Test
    public void scanItemFailureTest() {
        boolean scan = checkout.scan(null);

        assertFalse(scan);
        assertTrue(checkout.getItems().isEmpty());
    }
}