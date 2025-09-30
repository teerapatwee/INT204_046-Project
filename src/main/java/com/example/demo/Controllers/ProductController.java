package com.example.demo.Controllers;

import com.example.demo.Entities.Product;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products" ,service.getAll());
        return "products";
    }

    @GetMapping("/search")
    public String searchByProductName(@RequestParam String searchParam, Model model) {
        model.addAttribute("products",service.getByProductName(searchParam));
        return "products";
    }

    @GetMapping("/category")
    public String filterByProductLine(@RequestParam String category, Model model) {
        model.addAttribute("products",service.getByCategory(category));
        model.addAttribute("category", category);
        return "products";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        return "product_create";
    }

    @PostMapping("/create")
    public String createProduct(Product product ,Model model) throws IOException {
        Product p = service.addProduct(product);
        model.addAttribute("products", p);
        model.addAttribute("c_message", "Product Created successfully.");
        return "details_process";
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam String productCode , Model model ) {
        Product p = service.getById(productCode);
        model.addAttribute("products", p);
        return "product_update";
    }

    @PostMapping("/update")
    public String updateProduct(Product product ,Model model) throws IOException {
        Product p = service.update(product);
        model.addAttribute("products", p);
        model.addAttribute("u_message", "Product Updated successfully.");

        return "details_process";
    }

    @GetMapping("/details")
    public String getProductById(@RequestParam String productCode , Model model) {
        Product p = service.getById(productCode);
        model.addAttribute("products", p);
        return "product_details";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam String productCode , Model model) {
        Product p = service.deleteById(productCode);
        model.addAttribute("products", p);
        model.addAttribute("d_message", "Product Deleted successfully.");
        return "details_process";

    }


}
