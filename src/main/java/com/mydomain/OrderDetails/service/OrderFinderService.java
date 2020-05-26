package com.mydomain.OrderDetails.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.OrderDetails.dao.OrderDetailsRepository;
import com.mydomain.OrderDetails.entity.OrderDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderFinderService {
    Logger logger = LoggerFactory.getLogger(OrderFinderService.class);
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderFinderService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

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

    private void sortOrderDetails(List<OrderDetails> orderDetailsList) {
        orderDetailsList.sort((o1, o2) -> {
            if (o1.getVendor().getVendorid() > o2.getVendor().getVendorid()) {
                return 1;
            } else if (o1.getVendor().getVendorid() < o2.getVendor().getVendorid()) {
                return -1;
            } else return 0;
        });
    }

    private String updateOrderDates(List<OrderDetails> orderDetailsList, int index, Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        StringBuffer stringBuffer = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < index; i++) {
            c.setTime(date);
            c.add(Calendar.DATE, orderDetailsList.get(i).getDueperiod());
            OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsList.get(i).getOrderid()).get();
            orderDetails.setOrderdate(new Date(c.getTimeInMillis()));
            orderDetailsRepository.save(orderDetails);
            stringBuffer.append(mapper.writeValueAsString(orderDetailsList.get(i)));
            logger.info(stringBuffer.toString());
        }
        return stringBuffer.toString();

    }
}

