package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<Rent> rentList;

    public User(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "USER_ID", unique = true)
    public Long getUserId() {
        return userId;
    }

    @NonNull
    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @NonNull
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @NonNull
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Rent> getRentList() {
        return rentList;
    }
}
