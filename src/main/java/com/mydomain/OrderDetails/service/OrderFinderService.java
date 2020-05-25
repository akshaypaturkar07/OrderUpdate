package com.mydomain.OrderDetails.service;

import com.mydomain.OrderDetails.dao.OrderDetailsRepository;
import com.mydomain.OrderDetails.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderFinderService {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderFinderService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Iterable<OrderDetails> getListofOrders() {
        List<OrderDetails> orderDetailsList = (List<OrderDetails>) orderDetailsRepository.findAll();

        orderDetailsList.sort((o1, o2) -> {
            if (o1.getVendor().getVendorid() > o2.getVendor().getVendorid()) {
                return 1;
            } else if (o1.getVendor().getVendorid() < o2.getVendor().getVendorid()) {
                return -1;
            } else return 0;
        });
        int result = 0;
        for (int i = 0; i < orderDetailsList.size() - 1; i++) {
            System.out.print(orderDetailsList.get(i).getOrderamount() + "\n");
            result += orderDetailsList.get(i).getOrderamount();
            if (result < 0) {
                Date date = orderDetailsList.get(i).getOrderdate();
                updateOrderDates(orderDetailsList, i, date);
            }

        }
        return orderDetailsList;

    }

    public void updateOrderDates(List<OrderDetails> orderDetailsList, int index, Date date) {
        Calendar c = Calendar.getInstance();


        for (int i = 0; i < index; i++) {
            c.setTime(date);
            c.add(Calendar.DATE, orderDetailsList.get(i).getDueperiod());
            OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsList.get(i).getOrderid()).get();
            orderDetails.setOrderdate(new Date(c.getTimeInMillis()));
            orderDetailsRepository.save(orderDetails);
        }

    }
}

