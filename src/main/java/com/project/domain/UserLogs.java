package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_LOGS")
public class UserLogs {
    private Long userLoggerId;
    private LocalDateTime timeStamp;
    private String typeOfOperation;
    private String previousValue;
    private String newValue;

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "USER_LOG_ID", unique = true)
    public Long getUserLoggerId() {
        return userLoggerId;
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