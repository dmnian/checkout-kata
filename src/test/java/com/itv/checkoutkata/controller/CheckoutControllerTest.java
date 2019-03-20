package com.itv.checkoutkata.controller;

import com.itv.checkoutkata.DTO.ScanDTO;
import com.itv.checkoutkata.service.CheckoutService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CheckoutControllerTest {

    private CheckoutController checkoutController;
    private CheckoutService checkoutServiceMock;

    @Before
    public void setUp() throws Exception {
        checkoutServiceMock = mock(CheckoutService.class);
        checkoutController = new CheckoutController(checkoutServiceMock);
    }

    @Test
    public void scanCallCheckoutServiceTest() {
        ScanDTO scan = new ScanDTO();
        String itemName = "item-name";
        String uuid = "some-uuid";

        scan.setItemName(itemName);
        scan.setUuid(uuid);

        checkoutController.scan(scan);

        verify(checkoutServiceMock, times(1)).scan(uuid, itemName);
    }

    @Test
    public void sumCallCheckoutServiceTest() {
        String uuid = "some-uuid";

        checkoutController.sum(uuid);

        verify(checkoutServiceMock, times(1)).calculate(uuid);
    }
}