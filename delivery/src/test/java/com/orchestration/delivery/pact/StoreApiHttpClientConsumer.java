package com.orchestration.delivery.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslResponse;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.orchestration.delivery.dto.StoreSearchResponse;
import com.orchestration.delivery.properties.StoreApiProperties;
import com.orchestration.delivery.service.httpClient.StoreApiClientImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@SpringBootTest(classes = {StoreApiClientImpl.class})
@Import(TestConfig.class)
@PactTestFor(providerName = "store-service", port = "8080", pactVersion = PactSpecVersion.V2)
@ActiveProfiles("pact")
public class StoreApiHttpClientConsumer extends BasePact {

    @Pact(consumer = "delivery-service")
    public RequestResponsePact searchStore(PactDslWithProvider builder) {
        PactDslJsonBody pointDto = new PactDslJsonBody()
                .decimalType("x", 37.7749)
                .decimalType("y", -122.4194);

        PactDslJsonBody storeSearchDto = new PactDslJsonBody()
                .numberType("id", 1L)
                .stringType("name", "Store 1")
                .stringType("status", "ACTIVE")
                .stringType("workingStatus", "OPEN")
                .stringType("email", "example@store.com")
                .stringType("phone", "+1234567890")
                .stringType("logoUrl", "http://example.com/logo.png")
                .stringType("address", "123 Main St, City, Country")
                .stringType("deliveryType", "SCHEDULED")
                .minArrayLike("deliveryArea", 1, pointDto
                );

        PactDslJsonBody storeSearchResponse = new PactDslJsonBody()
                .minArrayLike("stores", 1, storeSearchDto);

        PactDslResponse response = builder
                .given("it_should_search")
                .uponReceiving("a request to search store list")
                .query("x=10.0&y=20.0")
                .method("GET")
                .path("/store/search")
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .body(storeSearchResponse);

        return response.toPact();
    }

    @Test
    @PactTestFor(pactMethod = "searchStore")
    void it_should_search_store(MockServer mockServer) {

        StoreApiProperties.baseUrl = mockServer.getUrl();
        StoreApiProperties.storeSearchUrl = "/store/search?";

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        StoreSearchResponse storeSearchResponseList = new StoreApiClientImpl(restTemplate).search(10.0, 20.0);

        assertThat(storeSearchResponseList).isNotNull();
    }

}
