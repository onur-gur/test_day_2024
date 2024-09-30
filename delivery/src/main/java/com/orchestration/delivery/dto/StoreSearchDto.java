package com.orchestration.delivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orchestration.delivery.model.DeliveryType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreSearchDto {
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

    public StoreSearchDto(Long id, String name, Status status, WorkingStatus workingStatus, String email, String phone, String logoUrl, String address, DeliveryType deliveryType, List<PointDto> deliveryArea) {
        this.id = id;
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

    public StoreSearchDto() {
    }

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
}
