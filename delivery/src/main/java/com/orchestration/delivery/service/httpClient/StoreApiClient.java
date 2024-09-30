package com.orchestration.delivery.service.httpClient;

import com.orchestration.delivery.dto.StoreSearchResponse;

public interface StoreApiClient {
    StoreSearchResponse search(Double lat, Double lon);
}
