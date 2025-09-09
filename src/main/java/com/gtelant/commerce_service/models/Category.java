package com.gtelant.commerce_service.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_update_at")
    private LocalDateTime lastUpdateAt;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
