package com.project.dto;

import com.project.domain.Game;
import com.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentDto {
    private Long rentId;
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RentDto rentDto = new RentDto();

        public Builder rentId(Long rentId) {
            rentDto.rentId = rentId;
            return this;
        }

        public Builder user(User user) {
            rentDto.user = user;
            return this;
        }

        public Builder game(Game game) {
            rentDto.game = game;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            rentDto.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            rentDto.endDate = endDate;
            return this;
        }

        public Builder price(double price) {
            rentDto.price = price;
            return this;
        }

        public RentDto build() {
            return rentDto;
        }
    }
}
