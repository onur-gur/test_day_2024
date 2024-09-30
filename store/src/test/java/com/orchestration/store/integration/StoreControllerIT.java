package com.orchestration.store.integration;

import com.orchestration.store.dto.StoreSearchResponse;
import com.orchestration.store.model.DeliveryType;
import com.orchestration.store.model.Status;
import com.orchestration.store.model.WorkingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class StoreControllerIT extends AbstractTestIT {
    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overrideProps(registry);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/storeSearch.sql")
    public void should_create_delivery() {
        //given

        //when
        ResponseEntity<StoreSearchResponse> response =
                testRestTemplate.exchange("/store/search?x=10.0&y=20.2", HttpMethod.GET, new HttpEntity<>(null, null), StoreSearchResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        StoreSearchResponse body = response.getBody();
        assertThat(body.getStores())
                .hasSize(1)
                .extracting("id", "name", "status", "working_status", "email", "phone", "logo_url", "address", "delivery_type")
                .containsExactlyInAnyOrder(
                        tuple(1L, "Sample Store", Status.ACTIVE, WorkingStatus.OPEN, "example@example.com", "123-456-7890", "http://example.com/logo.png", "123 Main St, City, Country", DeliveryType.SCHEDULED)
                );

        assertThat(body.getStores().get(0).getDeliveryArea())
                .extracting("x", "y")
                .containsExactlyInAnyOrder(
                        tuple(1.0, 2.0),
                        tuple(3.0, 4.0)
                );
    }
}
