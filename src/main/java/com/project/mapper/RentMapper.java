package com.project.mapper;

import com.project.domain.Rent;
import com.project.dto.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getUser(),
                rentDto.getGame(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                rentDto.getPrice()
        );
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getUser(),
                rent.getGame(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getPrice()
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
