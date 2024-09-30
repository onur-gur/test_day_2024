package com.orchestration.delivery.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StoreApiProperties {
    public static String baseUrl;
    public static String storeSearchUrl;

    //Value with setter injection
    @Value("${store-api.baseUrl}")
    public void setApiUrl(String baseUrl) {
        StoreApiProperties.baseUrl = baseUrl;
    }

    @Value("${store-api.storeSearchUrl}")
    public void setApiKey(String storeSearchUrl) {
        StoreApiProperties.storeSearchUrl = storeSearchUrl;
    }
}
