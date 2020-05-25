package com.mydomain.OrderDetails;

import com.mydomain.OrderDetails.service.OrderFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderDetailsApplication implements CommandLineRunner {
    @Autowired
    private OrderFinderService orderFinderService;

    public static void main(String[] args) {
        SpringApplication.run(OrderDetailsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderFinderService.getListofOrders();
    }
}
