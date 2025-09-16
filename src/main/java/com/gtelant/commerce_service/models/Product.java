package com.gtelant.commerce_service.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "reference", nullable = false)
    private String reference;
    @Column(name = "width", nullable = false)
    private double width;
    @Column(name = "height", nullable = false)
    private double height;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "sales", nullable = false)
    private int sales;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_update_at")
    private LocalDateTime lastUpdateAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

}
