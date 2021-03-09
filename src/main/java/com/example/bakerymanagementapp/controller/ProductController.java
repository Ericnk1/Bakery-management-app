package com.example.bakerymanagementapp.controller;

import com.example.bakerymanagementapp.exception.ProductNotFoundException;
import com.example.bakerymanagementapp.model.Product;
import com.example.bakerymanagementapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public List<Product> getEmployeeList() {
        return productRepository.findAll();

    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        Product oldProduct = getProduct(id);
        if (oldProduct != null) {
            productRepository.delete(oldProduct);
            productRepository.save(newProduct);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        if (getProduct(id) == null){
            throw new ProductNotFoundException();
        }else {
            productRepository.deleteById(id);
        }
    }
}
