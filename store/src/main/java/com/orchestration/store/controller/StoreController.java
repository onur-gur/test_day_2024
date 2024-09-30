package com.orchestration.store.controller;

import com.orchestration.store.dto.StoreSearchQuery;
import com.orchestration.store.dto.StoreSearchResponse;
import com.orchestration.store.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/search")
    public StoreSearchResponse searchStore(StoreSearchQuery query) {
        return storeService.search(query);
    }
}
