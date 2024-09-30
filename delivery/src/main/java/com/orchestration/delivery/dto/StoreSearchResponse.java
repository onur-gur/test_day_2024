package com.orchestration.delivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreSearchResponse {
    private List<StoreSearchDto> stores;

    public StoreSearchResponse() {
    }

    public StoreSearchResponse(List<StoreSearchDto> stores) {
        this.stores = stores;
    }

    public List<StoreSearchDto> getStores() {
        return stores;
    }

    public void setStores(List<StoreSearchDto> stores) {
        this.stores = stores;
    }
}
