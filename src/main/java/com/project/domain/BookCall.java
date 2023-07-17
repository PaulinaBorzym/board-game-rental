package com.project.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOK_CALL")
public class BookCall {
    private Long bookCallId;
    private LocalDate bookDate;
    private String phoneNumber;
    private String title;

    public BookCall(Long bookCallId, LocalDate bookDate, String phoneNumber, String title) {
        this.bookCallId = bookCallId;
        this.bookDate = bookDate;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "BOOK_CALL_ID", unique = true)
    public Long getBookCallId() {
        return bookCallId;
    }

    @NonNull
    @Column(name = "BOOK_DATE")
    public LocalDate getBookDate() {
        return bookDate;
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "Title")
    public String getTitle() {
        return title;
    }
}
