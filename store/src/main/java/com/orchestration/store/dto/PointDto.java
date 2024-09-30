package com.orchestration.store.dto;

import com.orchestration.store.model.PointDao;

public class PointDto {
    private double x;
    private double y;

    public PointDto() {
    }

    public PointDto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static PointDto from(PointDao point) {
        return new PointDto(point.getX(), point.getY());
    }
}
