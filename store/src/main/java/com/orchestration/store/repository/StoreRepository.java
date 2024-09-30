package com.orchestration.store.repository;

import com.orchestration.store.model.StoreDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreDao, Long> {

    @Query(value = "SELECT * FROM store s " +
            "WHERE JSON_CONTAINS(s.delivery_area, :x, '$[*].x') " +
            "AND JSON_CONTAINS(s.delivery_area, :y, '$[*].y')",
            nativeQuery = true)
    List<StoreDao> findByDeliveryAreaCoordinates(@Param("x") double x, @Param("y") double y);
}
