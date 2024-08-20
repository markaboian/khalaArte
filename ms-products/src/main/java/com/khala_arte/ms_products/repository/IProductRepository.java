package com.khala_arte.ms_products.repository;

import com.khala_arte.ms_products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1 ORDER BY p.name")
    Optional<Product> getProductByName(String name);
}
