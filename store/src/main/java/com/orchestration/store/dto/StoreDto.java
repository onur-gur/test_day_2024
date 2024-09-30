package com.orchestration.store.dto;

import com.orchestration.store.model.*;

import java.util.List;


public class StoreDto {
    private Long id;
    private String name;
    private Status status;
    private WorkingStatus workingStatus;
    private String email;
    private String phone;
    private String logoUrl;
    private String address;
    private DeliveryType deliveryType;
    private List<PointDto> deliveryArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<PointDto> getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(List<PointDto> deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public static StoreDto from(StoreDao storeDao) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(storeDao.getId());
        storeDto.setName(storeDao.getName());
        storeDto.setStatus(storeDao.getStatus());
        storeDto.setWorkingStatus(storeDao.getWorkingStatus());
        storeDto.setEmail(storeDao.getEmail());
        storeDto.setPhone(storeDao.getPhone());
        storeDto.setLogoUrl(storeDao.getLogoUrl());
        storeDto.setAddress(storeDao.getAddress());
        storeDto.setDeliveryType(storeDao.getDeliveryType());
        storeDto.setDeliveryArea(storeDao.getDeliveryArea().stream().map(PointDto::from).toList());

        return storeDto;
    }
}
