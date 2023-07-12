package com.project.service;

import com.project.domain.Rent;
import com.project.exeption.RentNotFoundException;
import com.project.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository repository;

    public List<Rent> getAllRents() {
        return repository.findAll();
    }

    public Rent getRent(final Long rentId) throws RentNotFoundException {
        return repository.findById(rentId).orElseThrow(RentNotFoundException::new);
    }

    public Rent saveRent(final Rent rent) {
        return repository.save(rent);
    }

    public void deleteRent(final Long id) {
        repository.deleteById(id);
    }
}
