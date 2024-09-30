package com.orchestration.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.delivery.dto.BuyerDto;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import org.hibernate.annotations.Type;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerDao {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private PointDao location;

    public BuyerDao(String firstName, String lastName, String email, String phoneNumber, String address, PointDao location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.location = location;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public PointDao getLocation() {
        return location;
    }

    public static BuyerDao toModel(BuyerDto command) {
        return new BuyerDao(command.getFirstName(), command.getLastName(), command.getEmail(), command.getPhoneNumber(), command.getAddress(), PointDao.toModel(command.getLocation()));
    }
}
