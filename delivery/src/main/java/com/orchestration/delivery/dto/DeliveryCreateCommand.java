package com.orchestration.delivery.dto;

import com.orchestration.delivery.model.DeliveryType;

import java.math.BigDecimal;

public class DeliveryCreateCommand {
    private BigDecimal price;
    private String description;
    private Integer quantity;
    private DeliveryType deliveryType;
    private BuyerDto buyer;
    private Long storeId;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BuyerDto getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDto buyer) {
        this.buyer = buyer;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
