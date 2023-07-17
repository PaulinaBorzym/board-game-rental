package com.project.repository;

import com.project.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);

    @Override
    Rent save(Rent rent);

    @Override
    void deleteById(Long id);
}
