package com.orchestration.store.dto;

import java.util.List;

public class StoreSearchResponse {
    private List<StoreDto> stores;

    public StoreSearchResponse(List<StoreDto> stores) {
        this.stores = stores;
    }

    public void setStores(List<StoreDto> stores) {
        this.stores = stores;
    }

    public List<StoreDto> getStores() {
        return stores;
    }
}
