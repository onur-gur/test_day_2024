package com.orchestration.store.model;

public class PointDao {
    private double x;
    private double y;

    public PointDao() {
    }

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
}
