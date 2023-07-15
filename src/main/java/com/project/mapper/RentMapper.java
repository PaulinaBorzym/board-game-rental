package com.project.mapper;

import com.project.domain.Rent;
import com.project.dto.RentDto;
import com.project.enums.CurrencyValue;
import com.project.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getRentId(),
                rentDto.getUser(),
                rentDto.getGame(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                MathUtils.roundToTwoDecimals(rentDto.getPrice()/CurrencyValue.INSTANCE.getValue())
        );
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getRentId(),
                rent.getUser(),
                rent.getGame(),
                rent.getStartDate(),
                rent.getEndDate(),
                MathUtils.roundToTwoDecimals(rent.getPrice()*CurrencyValue.INSTANCE.getValue())
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
