package com.orchestration.delivery.dto;

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
}
