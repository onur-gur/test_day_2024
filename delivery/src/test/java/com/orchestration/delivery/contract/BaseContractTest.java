package com.orchestration.delivery.contract;

import com.orchestration.delivery.DeliveryApplication;
import com.orchestration.delivery.controller.DeliveryController;
import com.orchestration.delivery.service.DeliveryService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = DeliveryApplication.class,
        properties = {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
        }
)
@AutoConfigureMockMvc
@ActiveProfiles("pact")
public class BaseContractTest {
    @Autowired
    private DeliveryController deliveryController;

    @MockBean
    private DeliveryService deliveryService;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.deliveryController);
    }

}
