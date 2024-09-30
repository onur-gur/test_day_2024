package com.orchestration.delivery.service;

import com.orchestration.delivery.dto.DeliveryCreateCommand;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {
    void create(DeliveryCreateCommand command);
}

