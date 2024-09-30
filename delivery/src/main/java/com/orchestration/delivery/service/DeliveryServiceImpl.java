package com.orchestration.delivery.service;

import com.orchestration.delivery.dto.DeliveryCreateCommand;
import com.orchestration.delivery.exception.NotFoundException;
import com.orchestration.delivery.model.DeliveryDao;
import com.orchestration.delivery.repository.DeliveryRepository;
import com.orchestration.delivery.service.httpClient.StoreApiClient;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final StoreApiClient storeApiClient;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, StoreApiClient storeApiClient) {
        this.deliveryRepository = deliveryRepository;
        this.storeApiClient = storeApiClient;
    }

    @Override
    public void create(DeliveryCreateCommand command) {
        if (!validateStore(command)) {
            throw new NotFoundException("no store found in your area");
        }

        DeliveryDao deliveryDao = DeliveryDao.toModel(command);

        deliveryRepository.save(deliveryDao);
    }

    private boolean validateStore(DeliveryCreateCommand command) {
        return storeApiClient.search(command.getBuyer().getLocation().getX(), command.getBuyer().getLocation().getY())
                .getStores().stream().anyMatch(store -> store.getId().equals(command.getStoreId()));
    }
}
