package com.orchestration.store.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import com.orchestration.store.StoreApplication;
import com.orchestration.store.model.*;
import com.orchestration.store.repository.StoreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@Provider("store-service")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StoreApplication.class,
        properties = {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
        })
@PactBroker(host = "localhost", port = "80", authentication = @PactBrokerAuth(username = "test_day", password = "test_day"))
public class StoreApiProvider {

    @LocalServerPort
    int port;

    @MockBean
    private StoreRepository storeRepository;

    @BeforeAll
    public static void start() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("it_should_search")
    void it_should_search() {
        when(storeRepository.findByDeliveryAreaCoordinates(anyDouble(), anyDouble())).thenReturn(List.of(createResponse()));
    }

    private StoreDao createResponse() {
        PointDao point1 = new PointDao(37.7749, -122.4194);
        PointDao point2 = new PointDao(3.0, 4.0);

        StoreDao storeDao = new StoreDao(
                1L,
                "Store 1",
                Status.ACTIVE,
                WorkingStatus.OPEN,
                "example@store.com",
                "+1234567890",
                "http://example.com/logo.png",
                "123 Main St, City, Country",
                DeliveryType.SCHEDULED,
                List.of(point1, point2));

        return storeDao;
    }
}
