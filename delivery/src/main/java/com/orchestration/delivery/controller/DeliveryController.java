package com.orchestration.delivery.controller;

import com.orchestration.delivery.dto.DeliveryCreateCommand;
import com.orchestration.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void createDelivery(@RequestBody DeliveryCreateCommand command) {
        deliveryService.create(command);
    }
}
