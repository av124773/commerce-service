package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.dtos.ProductRequest;
import com.gtelant.commerce_service.dtos.ProductResponse;
import com.gtelant.commerce_service.mappers.ProductMapper;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class ProductConrotller {
    private final ProductService productService;
    private final ProductMapper productMapper;
    
    public ProductConrotller(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Operation(summary = "取得所有Products列表", description = "")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts().stream()
                .map(productMapper::toResponse)
                .toList()
        );
    }

    @Operation(summary = "取得所有Products列表(Page)",  
            description = """                    
                    參數說明：
                    - page: 當前想要輸出第幾頁的資料。
                    - size: 每一頁最多幾筆資料。
                    - queryName: 搜尋產品關鍵字。
                    - categoryId: 產品的分類編號。
                    - stockFrom: 庫存量下限。
                    - stockTo: 庫存量上限。
                    - sales: 產品銷量狀態編號。
                      - 0: 無銷量。
                      - 1: 低銷量 1~99。
                      - 2: 平均銷量 100~499。
                      - 3: 高銷量 500 以上。
                    """)
    @GetMapping("/page")
    public Page<ProductResponse> getAllProductsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String queryName,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer stockFrom,
            @RequestParam(required = false) Integer stockTo,
            @RequestParam(required = false) Integer sales
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getAllProducts(queryName, categoryId, stockFrom, stockTo, sales, pageRequest).map(productMapper::toResponse);
    }

    @Operation(summary = "取得指定Product", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(productMapper.toResponse(product.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "建立新的Product", description = "")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.toResponse(savedProduct));
    }

    @Operation(summary = "更新指定Product", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody ProductRequest request) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product updatedProduct = productMapper.updateEntity(product.get(), request);
            Product savedProduct = productService.saveProduct(updatedProduct);
            return ResponseEntity.ok(productMapper.toResponse(savedProduct));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "刪除指定Product", description = "")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
