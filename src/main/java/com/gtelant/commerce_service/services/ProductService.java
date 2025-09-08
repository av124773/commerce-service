package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
