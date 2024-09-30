package com.orchestration.store.model;

import com.orchestration.store.model.converter.PointDaoListConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "store")
public class StoreDao {
    @Id
    private Long id;
    private String name;
    private Status status;
    private WorkingStatus workingStatus;
    private String email;
    private String phone;
    private String logoUrl;
    private String address;
    private DeliveryType deliveryType;
    @Convert(converter = PointDaoListConverter.class)
    @Column(columnDefinition = "json")
    private List<PointDao> deliveryArea;

    public StoreDao() {
    }

    public StoreDao(String name, Status status, WorkingStatus workingStatus, String email, String phone, String logoUrl, String address, DeliveryType deliveryType, List<PointDao> deliveryArea) {
        this.name = name;
        this.status = status;
        this.workingStatus = workingStatus;
        this.email = email;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.address = address;
        this.deliveryType = deliveryType;
        this.deliveryArea = deliveryArea;
    }

    public StoreDao(Long id, String name, Status status, WorkingStatus workingStatus, String email, String phone, String logoUrl, String address, DeliveryType deliveryType, List<PointDao> deliveryArea) {
        this.name = name;
        this.id = id;
        this.status = status;
        this.workingStatus = workingStatus;
        this.email = email;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.address = address;
        this.deliveryType = deliveryType;
        this.deliveryArea = deliveryArea;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public List<PointDao> getDeliveryArea() {
        return deliveryArea;
    }
}
