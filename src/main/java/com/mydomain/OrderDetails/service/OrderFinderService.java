package com.mydomain.OrderDetails.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.OrderDetails.dao.OrderDetailsRepository;
import com.mydomain.OrderDetails.dao.VendorRepository;
import com.mydomain.OrderDetails.entity.OrderDetails;
import com.mydomain.OrderDetails.entity.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OrderFinderService implements IOrderFinderService {
    Logger logger = LoggerFactory.getLogger(OrderFinderService.class);
    private OrderDetailsRepository orderDetailsRepository;
    private VendorRepository vendorRepository;
    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public OrderFinderService(OrderDetailsRepository orderDetailsRepository, VendorRepository vendorRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.vendorRepository = vendorRepository;

    }


    @Override
    public String getVendors() {
        List<Vendor> vendors = (List<Vendor>) vendorRepository.findAll();
        return vendors.isEmpty() ? "No vendor found .. !" : jsonHelper(vendors);
    }

    @Override
    public String getOrders() {
        List<OrderDetails> orderDetails = (List<OrderDetails>) orderDetailsRepository.findAll();
        return orderDetails.isEmpty() ? "No orderDetails found .. !" : jsonHelper(orderDetails);
    }


    @Override
    public String updateOrders() {
        String recordsUpdated = null;
        List<OrderDetails> orderDetailsList = (List<OrderDetails>) orderDetailsRepository.findAll();
        this.sortOrderDetails(orderDetailsList);
        int result = 0;
        for (int i = 0; i < orderDetailsList.size() - 1; i++) {
            logger.info(orderDetailsList.get(i).getOrderamount() + "\n");
            result += orderDetailsList.get(i).getOrderamount();
            if (result < 0) {
                Date date = orderDetailsList.get(i).getOrderdate();
                try {
                    recordsUpdated = updateOrderDates(orderDetailsList, i, date);

                } catch (Exception e) {
                    logger.error("Update failed with error , please check log for more details ");
                    e.printStackTrace();
                    return "Failed";
                }

            }
        }
        return recordsUpdated;
    }

    @Override
    public String updateOrderDates(List<OrderDetails> orderDetailsList, int index, Date date) throws JsonProcessingException {
        Calendar c = Calendar.getInstance();
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < index; i++) {
            c.setTime(date);
            c.add(Calendar.DATE, orderDetailsList.get(i).getDueperiod());
            OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsList.get(i).getOrderid()).get();
            orderDetails.setOrderdate(new Date(c.getTimeInMillis()));
            orderDetailsRepository.save(orderDetails);
            stringBuffer.append(mapper.writeValueAsString(orderDetailsList.get(i)));
            logger.info(stringBuffer.toString());
        }
        return "Success " + stringBuffer.toString();
    }


}

