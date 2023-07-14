package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Currency")
public class Currency {
    private Long currencyId;
    private String currencyCode;
    private double value;

    public Currency(String currencyCode, double value) {
        this.currencyCode = currencyCode;
        this.value = value;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "CURRENCY_ID", unique = true)
    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @NonNull
    @Column(name = "CURRENCY_CODE")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @NonNull
    @Column(name = "VALUE")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
