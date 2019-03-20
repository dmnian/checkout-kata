package com.itv.checkoutkata.service;

import com.itv.checkoutkata.DTO.CheckoutDTO;
import com.itv.checkoutkata.model.Checkout;
import com.itv.checkoutkata.model.Item;
import com.itv.checkoutkata.repository.ItemRuleRepository;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private ItemRuleRepository itemRulesRepository;

    private Map<String, Item> itemMap;
    private Map<String, Checkout> recentCheckouts;

    @Before
    public void setUp() throws Exception {
        itemRulesRepository = mock(ItemRuleRepository.class);

        itemMap = new HashMap<>();
        recentCheckouts = new HashMap<>();

        checkoutService = new CheckoutService(new ModelMapper(), itemRulesRepository, null);
        checkoutService = new CheckoutService(new ModelMapper(), itemRulesRepository, null);
        checkoutService.setItemMap(itemMap);
        checkoutService.setRecentCheckouts(recentCheckouts);
    }

    @Test
    public void scanWhenUuidDoesNotExistTest() {
        itemMap.put("A", new Item("A", 50));

        String uuid = "some-unexisting-uuid";
        CheckoutDTO checkoutDTO = checkoutService.scan(uuid, "A");
        CheckoutDTO checkoutDTOSecond = checkoutService.scan(checkoutDTO.getUuid(), "A");

        assertEquals(1, recentCheckouts.size());
        assertEquals(1, checkoutDTO.getItems().size());
        assertEquals(2, checkoutDTOSecond.getItems().size());
        assertEquals("A", checkoutDTO.getItems().get(0).getName());
        assertNotEquals("some-unexisting-uuid", checkoutDTO.getUuid());
    }


    @Test
    public void scanWhenUuidAlreadyExistTest() {
        String uuid = "some-uuid";
        Checkout checkout = new Checkout();
        checkout.setUuid(uuid);
        recentCheckouts.put(uuid, checkout);
        itemMap.put("A", new Item("A", 50));

        CheckoutDTO checkoutDTO = checkoutService.scan(uuid, "A");

        assertEquals(1, recentCheckouts.size());
        assertEquals(1, checkoutDTO.getItems().size());
        assertEquals("A", checkoutDTO.getItems().get(0).getName());
        assertEquals(uuid, checkout.getUuid());
    }

    @Test
    public void scanWhenUuidAlreadyExistButItemListEmptyTest() {
        String uuid = "some-uuid";
        Checkout checkout = new Checkout();
        checkout.setUuid(uuid);
        recentCheckouts.put(uuid, checkout);

        CheckoutDTO checkoutDTO = checkoutService.scan(uuid, "A");

        assertEquals(1, recentCheckouts.size());
        assertEquals(0, checkoutDTO.getItems().size());
        assertEquals(uuid, checkout.getUuid());
    }

    @Test
    public void calculateCheckoutDoesNotExistTest() {
        int result = checkoutService.calculate("some-unexisting-uuid");

        assertEquals(0, result);
    }

    @Test
    public void calculateCheckoutTest() {
        Checkout checkoutMock = mock(Checkout.class);
        when(checkoutMock.calculate()).thenReturn(1);
        recentCheckouts.put("some-uuid", checkoutMock);

        int result = checkoutService.calculate("some-uuid");

        verify(checkoutMock, times(1)).calculate();
        assertEquals(1, result);
    }
}