package com.project.repository;

import com.project.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Override
    List<Currency> findAll();

    Optional<Currency> findByCurrencyCode(String currencyCode);

    @Override
    Currency save(Currency currency);

}
