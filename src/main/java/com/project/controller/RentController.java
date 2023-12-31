package com.project.controller;

import com.project.domain.Rent;
import com.project.dto.RentDto;
import com.project.enums.LogType;
import com.project.exeption.RentNotFoundException;
import com.project.mapper.RentMapper;
import com.project.service.LoggerService;
import com.project.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rents")
public class RentController {
    private final RentService service;
    private final LoggerService logger;
    private final RentMapper rentMapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents() {
        List<Rent> rents = service.getAllRents();
        return ResponseEntity.ok(rentMapper.mapToRentDtoList(rents));
    }

    @GetMapping(value = "{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return ResponseEntity.ok(rentMapper.mapToRentDto(service.getRent(rentId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        service.saveRent(rent);
        logger.saveRentLog(LogType.CREATE, rent);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        logger.saveRentLog(LogType.UPDATE, rent);
        Rent savedRent = service.saveRent(rent);
        return ResponseEntity.ok(rentMapper.mapToRentDto(savedRent));
    }

    @DeleteMapping(value = "{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) throws RentNotFoundException {
        logger.saveRentLog(LogType.DELETE, service.getRent(rentId));
        service.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }
}
