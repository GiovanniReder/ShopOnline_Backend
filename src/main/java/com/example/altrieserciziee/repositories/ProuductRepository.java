package com.example.altrieserciziee.repositories;

import com.example.altrieserciziee.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProuductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByTitle(String place);
    List<Product> findByCategory(String place);
}
