package com.orchestration.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.delivery.dto.PointDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointDao {
    private double x;
    private double y;

    public PointDao(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static PointDao toModel(PointDto pointDto) {
        return new PointDao(pointDto.getX(), pointDto.getY());
    }
}
