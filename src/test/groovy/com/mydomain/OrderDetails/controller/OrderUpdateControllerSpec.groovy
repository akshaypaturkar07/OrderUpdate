package com.mydomain.OrderDetails.controller


import com.mydomain.OrderDetails.dao.OrderDetailsRepository
import com.mydomain.OrderDetails.service.OrderFinderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(controllers = [OrderUpdateController])
class OrderUpdateControllerSpec extends Specification {
    @Autowired
    protected MockMvc mockMvc

    @Autowired
    OrderFinderService orderFinderService = Mock()
    @Autowired
    OrderDetailsRepository orderDetailsRepository = Mock()

    def "when get is performed then the response has status 200 and content is 'Order Updated Successfully .. ! '"() {
        when:
        def result = mockMvc.perform(get('/updateOrders'))

        then:
        0 * _._

        and:
        result.andExpect(status().is(200))

    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        OrderFinderService orderFinderService() {
            return detachedMockFactory.Stub(OrderFinderService)
        }

        @Bean
        OrderDetailsRepository orderDetailsRepository() {
            return detachedMockFactory.Stub(OrderDetailsRepository)

        }
    }


}
