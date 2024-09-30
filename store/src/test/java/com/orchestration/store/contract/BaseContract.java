package com.orchestration.store.contract;

import com.orchestration.store.StoreApplication;
import com.orchestration.store.controller.StoreController;
import com.orchestration.store.dto.PointDto;
import com.orchestration.store.dto.StoreDto;
import com.orchestration.store.dto.StoreSearchQuery;
import com.orchestration.store.dto.StoreSearchResponse;
import com.orchestration.store.model.DeliveryType;
import com.orchestration.store.model.Status;
import com.orchestration.store.model.WorkingStatus;
import com.orchestration.store.service.StoreService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(
        classes = StoreApplication.class,
        properties = {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
        }
)
@AutoConfigureMockMvc
@ActiveProfiles("contract")
public class BaseContract {
    @Autowired
    private StoreController storeController;
    @MockBean
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        Mockito.when(this.storeService.search(any(StoreSearchQuery.class))).thenReturn(createResponse());
        RestAssuredMockMvc.standaloneSetup(this.storeController);
    }

    private StoreSearchResponse createResponse() {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(1L);
        storeDto.setName("Sample Entity");
        storeDto.setStatus(Status.ACTIVE);
        storeDto.setWorkingStatus(WorkingStatus.OPEN);
        storeDto.setEmail("example@example.com");
        storeDto.setPhone("123-456-7890");
        storeDto.setLogoUrl("http://example.com/logo.png");
        storeDto.setAddress("123 Main St, City, Country");
        storeDto.setDeliveryType(DeliveryType.SCHEDULED);

        // Create some deliveryArea points
        PointDto point1 = new PointDto(1.0, 2.0);
        PointDto point2 = new PointDto(3.0, 4.0);
        storeDto.setDeliveryArea(Arrays.asList(point1, point2));


        return new StoreSearchResponse(List.of(storeDto));
    }

}
