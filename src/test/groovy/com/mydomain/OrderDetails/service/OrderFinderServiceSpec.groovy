package com.mydomain.OrderDetails.service

import com.mydomain.OrderDetails.dao.OrderDetailsRepository
import com.mydomain.OrderDetails.entity.OrderDetails
import com.mydomain.OrderDetails.entity.Vendor
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import java.sql.Date

class OrderFinderServiceSpec extends Specification {

    OrderDetailsRepository orderDetailsRepository = Mock()
    OrderFinderService orderFinderService = null

    def setup() {
        orderFinderService = new OrderFinderService(orderDetailsRepository)
    }

    def "testing updateOrder method of service class for positive scenario"() {
        given:
        List<OrderDetails> orderDetailsList = Arrays.asList(
                new OrderDetails(1, new Vendor(1, "amazon"), 100, new Date(2018, 10, 20), 30),
                new OrderDetails(3, new Vendor(1, "amazon"), 100, new Date(2018, 07, 20), 25),
                new OrderDetails(4, new Vendor(1, "amazon"), -250, new Date(2018, 01, 07), 45),
                new OrderDetails(5, new Vendor(2, "flipkart"), 450, new Date(2018, 03, 05), 30),
                new OrderDetails(6, new Vendor(3, "ebay"), 78, new Date(2018, 05, 05), 30),
                new OrderDetails(2, new Vendor(2, "flipkart"), 100, new Date(2018, 8, 20), 15),)

        when:
        def result = orderFinderService.updateOrders()

        then:
        1 * orderDetailsRepository.findAll() >> orderDetailsList
        1 * orderDetailsRepository.findById(id) >> orderDetails
        1 * orderDetailsRepository.save(orderDetails)
        0 * _._

        and:
        result.contains("Success")

        where:
        id | orderDetails
        1  | new OrderDetails(1, new Vendor(1, "amazon"), 100, new Date(2018, 10, 20), 30)
        3  | new OrderDetails(3, new Vendor(1, "amazon"), 100, new Date(2018, 07, 20), 25)
        4  | new OrderDetails(4, new Vendor(1, "amazon"), -250, new Date(2018, 01, 07), 45)
        5  | new OrderDetails(5, new Vendor(2, "flipkart"), 450, new Date(2018, 03, 05), 30)
        6  | new OrderDetails(6, new Vendor(3, "ebay"), 78, new Date(2018, 05, 05), 30)
        2  | new OrderDetails(2, new Vendor(2, "flipkart"), 100, new Date(2018, 8, 20), 15)


    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        OrderDetailsRepository orderDetailsRepository() {
            return detachedMockFactory.Stub(OrderDetailsRepository)

        }
    }
}
