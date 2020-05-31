package com.mydomain.OrderDetails.controller;

import com.mydomain.OrderDetails.service.OrderFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/updateOrders")
public class OrderUpdateController {
    Logger logger = LoggerFactory.getLogger(OrderUpdateController.class);

    @Autowired
    public OrderUpdateController(OrderFinderService orderFinderService) {
        this.orderFinderService = orderFinderService;
    }

    private OrderFinderService orderFinderService;


    @GetMapping
    public String updateOrders() {
        String result = orderFinderService.updateOrders();
        logger.info("Orders update successfully .. !");
        logger.info(result);
        return "Order Updated Successfully .. ! ";
    }
}
