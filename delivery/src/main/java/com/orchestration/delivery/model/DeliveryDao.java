package com.orchestration.delivery.model;

import com.orchestration.delivery.dto.DeliveryCreateCommand;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Entity(name = "delivery")
@Table(name = "delivery")
public class DeliveryDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String orderId;
    private BigDecimal price;
    private String description;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private BuyerDao buyer;
    private Long storeId;

    public DeliveryDao(BigDecimal price, String description, Integer quantity, DeliveryType deliveryType, Long storeId, BuyerDao buyer) {
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.deliveryType = deliveryType;
        this.storeId = storeId;
        this.buyer = buyer;
    }

    public DeliveryDao() {
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public BuyerDao getBuyer() {
        return buyer;
    }

    public Long getStoreId() {
        return storeId;
    }

    public static DeliveryDao toModel(DeliveryCreateCommand command) {
        return new DeliveryDao(command.getPrice(), command.getDescription(), command.getQuantity(), command.getDeliveryType(), command.getStoreId(), BuyerDao.toModel(command.getBuyer()));
    }
}
