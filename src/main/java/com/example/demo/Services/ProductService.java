package com.example.demo.Services;

import com.example.demo.Entities.Product;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(String code) {
        return repository.findById(code).orElse(null);
    }

    public List<Product> getByProductName(String searchParam) {
        return repository.findProductsByProductName("%"+ searchParam + "%");
    }

    public List<Product> getByCategory(String category) {
        if(Objects.equals(category, "all")) return getAll();
        return repository.findProductsByProductLine(category);
    }

    public Product addProduct(Product p) {
        return repository.save(p);
    }

    public Product deleteById(String id) {
        Product p = getById(id);
        repository.deleteById(id);
        return p;
    }

    public Product update(Product p) {
        return repository.save(p);
    }







}
