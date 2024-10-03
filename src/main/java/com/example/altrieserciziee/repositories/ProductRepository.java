package com.example.altrieserciziee.repositories;

import com.example.altrieserciziee.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByTitle(String title);
    List<Product> findByCategory(String category);
    List<Product> findByIsOnSale(Boolean isOnSale);
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
}
