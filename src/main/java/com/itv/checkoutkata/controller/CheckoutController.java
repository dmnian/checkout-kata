package com.itv.checkoutkata.controller;

import com.itv.checkoutkata.DTO.CheckoutDTO;
import com.itv.checkoutkata.DTO.ScanDTO;
import com.itv.checkoutkata.service.CheckoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Handles item scanning and calculates final bill.")
public class CheckoutController {
    private Logger logger = LoggerFactory.getLogger(CheckoutController.class);
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("scan")
    @ApiOperation("Scans next element into existing checkout or create new with one element")
    public CheckoutDTO scan(@ApiParam("Provide uuid to continue scanning or empty string to generate new uuid and start new scanning.")
                            @RequestBody ScanDTO scan) {
        return checkoutService.scan(scan.getUuid(), scan.getItemName());
    }

    @PostMapping("sum")
    @ApiOperation("Calculates and returns sum of the bill")
    public Integer sum(@ApiParam("UUID of checkout.") @RequestBody String uuid) {
        return checkoutService.calculate(uuid);

    }


}
