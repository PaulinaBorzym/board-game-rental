package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY_LOGS")
public class CurrencyLogs {
    private Long currencyLoggerId;
    private LocalDateTime timeStamp;
    private double PLN;
    private double EUR;
    private double USD;

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "CURRENCY_LOG_ID", unique = true)
    public Long getCurrencyLoggerId() {
        return currencyLoggerId;
    }

    @NonNull
    @Column(name = "TIME_STAMP")
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Column(name = "PLN")
    public double getPLN() {
        return PLN;
    }

    @Column(name = "EUR")
    public double getEUR() {
        return EUR;
    }

    @Column(name = "USD")
    public double getUSD() {
        return USD;
    }
}
