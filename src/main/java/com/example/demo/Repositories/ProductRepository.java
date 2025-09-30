package com.example.demo.Repositories;

import com.example.demo.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("""
            select p from Product p
            where p.productLine = ?1
    """)
    List<Product> findProductsByProductLine(String category);

    @Query("""
            select p from Product p
            where p.productName like ?1
            """)
    List<Product> findProductsByProductName(String searchParam);




}