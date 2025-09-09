package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.repositories.ProductRepository;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Product> getAllProducts(String queryName, Integer categoryId, Integer stock, Integer sales, PageRequest pageRequest) {
        Specification<Product> spec = productSpecification(queryName, categoryId, stock, sales);
        return productRepository.findAll(spec, pageRequest);
    }

    private Specification<Product> productSpecification(String queryName, Integer categoryId, Integer stock, Integer sales) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (queryName != null && !queryName.isEmpty()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("reference")), "%" + queryName.toLowerCase() + "%"
                    )
                );
            }

            if (categoryId != null) {
                predicates.add(
                    criteriaBuilder.equal(root.get("category").get("id"), categoryId)
                );
            }

            if (stock != null) {
                if (stock == 0) {
                    predicates.add(criteriaBuilder.equal(root.get("stock"), 0));
                } else if (stock == 1) {
                    predicates.add(criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), 1),
                        criteriaBuilder.lessThanOrEqualTo(root.get("stock"), 9)
                    ));
                } else if (stock == 2) {
                    predicates.add(criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), 10),
                        criteriaBuilder.lessThanOrEqualTo(root.get("stock"), 49)
                    ));
                } else if (stock == 3) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), 50));
                }
            }

            if (sales != null) {
                if (sales == 0) {
                    predicates.add(criteriaBuilder.equal(root.get("sales"), 0));
                } else if (sales == 1) {
                    predicates.add(criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("sales"), 1),
                        criteriaBuilder.lessThanOrEqualTo(root.get("sales"), 99)
                    ));
                } else if (sales == 2) {
                    predicates.add(criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("sales"), 100),
                        criteriaBuilder.lessThanOrEqualTo(root.get("sales"), 499)
                    ));
                } else if (sales == 3) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sales"), 500));
                }
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
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
