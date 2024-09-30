package com.orchestration.store.service;

import com.orchestration.store.dto.StoreSearchQuery;
import com.orchestration.store.dto.StoreSearchResponse;

public interface StoreService {
    StoreSearchResponse search(StoreSearchQuery query);
}
