package com.example.demo.repository;

import com.example.demo.Model.Currency;
import com.example.demo.Model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Query("SELECT cc from Currency cc WHERE "+
            "LOWER(cc.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    public Currency getConversionByCurrency(String keyword);
}
