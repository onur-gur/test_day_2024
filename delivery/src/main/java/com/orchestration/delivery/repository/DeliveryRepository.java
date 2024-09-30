package com.orchestration.delivery.repository;

import com.orchestration.delivery.model.DeliveryDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryDao, UUID> {
}
