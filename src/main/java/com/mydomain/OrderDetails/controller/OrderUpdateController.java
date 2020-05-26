package com.mydomain.OrderDetails.controller;

import com.mydomain.OrderDetails.service.OrderFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderUpdateController {
    Logger logger = LoggerFactory.getLogger(OrderUpdateController.class);


    private OrderFinderService orderFinderService;

    @Autowired
    public OrderUpdateController(OrderFinderService orderFinderService) {
        this.orderFinderService = orderFinderService;
    }

    //@GetMapping("/updateOrders")
    @RequestMapping(value = "/updateOrders", method = RequestMethod.GET)
    public String updateOrders() {
        String result = orderFinderService.updateOrders();
        logger.info("Orders update successfully .. !");
        return "Order Updated Successfully .. ! \n" + result;
    }
}
