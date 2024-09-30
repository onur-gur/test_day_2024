package com.orchestration.delivery.service.httpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestration.delivery.dto.StoreSearchResponse;
import com.orchestration.delivery.properties.StoreApiProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StoreApiClientImpl implements StoreApiClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StoreApiClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public StoreSearchResponse search(Double x, Double y) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getStoreApiUrl(x, y), String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(), StoreSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStoreApiUrl(Double x, Double y) {
        return StoreApiProperties.baseUrl + StoreApiProperties.storeSearchUrl + "x=" + x + "&y=" + y;
    }
}
