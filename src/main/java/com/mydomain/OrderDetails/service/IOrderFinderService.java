package com.mydomain.OrderDetails.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.OrderDetails.entity.OrderDetails;

import java.sql.Date;
import java.util.List;

public interface IOrderFinderService {
    ObjectMapper mapper = new ObjectMapper();

    String getVendors();

    String getOrders();

    String updateOrders();

    String updateOrderDates(List<OrderDetails> orderDetailsList, int index, Date date) throws JsonProcessingException;


    default void sortOrderDetails(List<OrderDetails> orderDetailsList) {
        orderDetailsList.sort((o1, o2) -> {
            if (o1.getVendor().getVendorid() > o2.getVendor().getVendorid()) {
                return 1;
            } else if (o1.getVendor().getVendorid() < o2.getVendor().getVendorid()) {
                return -1;
            } else return 0;
        });
    }

    default String jsonHelper(List<? extends Object> objects) {
        StringBuilder stringBuffer = new StringBuilder();
        objects.forEach(o -> {
            try {
                stringBuffer.append(mapper.writeValueAsString(o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });
        return stringBuffer.toString();
    }
}
