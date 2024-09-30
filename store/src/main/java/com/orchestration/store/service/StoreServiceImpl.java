package com.orchestration.store.service;

import com.orchestration.store.dto.StoreDto;
import com.orchestration.store.dto.StoreSearchQuery;
import com.orchestration.store.dto.StoreSearchResponse;
import com.orchestration.store.model.StoreDao;
import com.orchestration.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreSearchResponse search(StoreSearchQuery query) {
        List<StoreDao> storeDaos = storeRepository.findByDeliveryAreaCoordinates(query.getX(), query.getY());

        return new StoreSearchResponse(storeDaos.stream().map(StoreDto::from).toList());
    }
}
