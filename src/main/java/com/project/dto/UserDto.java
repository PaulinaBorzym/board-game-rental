package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserDto userDto = new UserDto();

        public Builder userId(Long userId) {
            userDto.userId = userId;
            return this;
        }

        public Builder firstName(String firstName) {
            userDto.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userDto.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            userDto.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            userDto.phoneNumber = phoneNumber;
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}