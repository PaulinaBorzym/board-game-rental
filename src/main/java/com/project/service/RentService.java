package com.project.service;

import com.project.domain.Rent;
import com.project.exeption.RentNotFoundException;
import com.project.repository.RentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentService {
    private  RentRepository repository;

    @Autowired
    public RentService(RentRepository repository) {
        this.repository = repository;
    }

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
