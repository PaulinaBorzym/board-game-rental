package com.project.enums;

public enum CurrencyValue {
    INSTANCE;
    private double value = 1.0;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
