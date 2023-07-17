package com.project.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "RENT_LOGS")
public class RentLogs {
    private Long rentLoggerId;
    private LocalDateTime timeStamp;
    private String typeOfOperation;
    private String previousValue;
    private String newValue;

    public RentLogs(Long rentLoggerId, LocalDateTime timeStamp, String typeOfOperation, String previousValue, String newValue) {
        this.rentLoggerId = rentLoggerId;
        this.timeStamp = timeStamp;
        this.typeOfOperation = typeOfOperation;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "RENT_LOG_ID", unique = true)
    public Long getRentLoggerId() {
        return rentLoggerId;
    }

    @NonNull
    @Column(name = "TIME_STAMP")
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @NonNull
    @Column(name = "TYPE_OF_OPERATION")
    public String getTypeOfOperation() {
        return typeOfOperation;
    }

    @Column(name = "PREVIOUS_VALUE")
    public String getPreviousValue() {
        return previousValue;
    }

    @Column(name = "NEW_VALUE")
    public String getNewValue() {
        return newValue;
    }
}
