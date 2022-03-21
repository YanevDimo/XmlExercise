package com.example.xmlexercise.repository;

import com.example.xmlexercise.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal lower, BigDecimal upper);
}
