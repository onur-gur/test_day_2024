package com.orchestration.delivery.pact;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import com.orchestration.delivery.dto.PointDto;
import com.orchestration.delivery.dto.StoreSearchDto;
import com.orchestration.delivery.dto.StoreSearchResponse;

import java.lang.reflect.Field;

import static com.orchestration.delivery.pact.BasePact.getDeclaredFields;

public class PactDlsBodyCreateV2 {

    PactDslJsonBody generateBody() {
        Field[] storeSearchFields = getDeclaredFields(StoreSearchResponse.class);
        Field[] storeDtoFields = getDeclaredFields(StoreSearchDto.class);
        Field[] deliveryAreaFields = getDeclaredFields(PointDto.class);

        PactDslJsonBody pointDto = new PactDslJsonBody()
                .decimalType(deliveryAreaFields[0].getName(), 37.7749)
                .decimalType(deliveryAreaFields[1].getName(), -122.4194);

        PactDslJsonBody storeSearchDto = new PactDslJsonBody()
                .numberType(storeDtoFields[0].getName(), 1L)
                .stringType(storeDtoFields[1].getName(), "Store 1")
                .stringType(storeDtoFields[2].getName(), "ACTIVE")
                .stringType(storeDtoFields[3].getName(), "OPEN")
                .stringType(storeDtoFields[4].getName(), "example@store.com")
                .stringType(storeDtoFields[5].getName(), "+1234567890")
                .stringType(storeDtoFields[6].getName(), "http://example.com/logo.png")
                .stringType(storeDtoFields[7].getName(), "123 Main St, City, Country")
                .stringType(storeDtoFields[8].getName(), "SCHEDULED")
                .minArrayLike(storeDtoFields[9].getName(), 1, pointDto);

        PactDslJsonBody storeSearchResponse = new PactDslJsonBody()
                .minArrayLike(storeSearchFields[0].getName(), 1, storeSearchDto);

        return storeSearchResponse;
    }

}
