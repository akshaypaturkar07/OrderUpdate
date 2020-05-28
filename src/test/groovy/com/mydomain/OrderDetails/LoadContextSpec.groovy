package com.mydomain.OrderDetails

import com.mydomain.OrderDetails.controller.OrderUpdateController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextSpec extends Specification {
    @Autowired
    private OrderUpdateController orderUpdateController

    def "when context is loaded then all expected beans are created"() {
        expect: "the orderUpdateController is created"
        orderUpdateController
    }

}
