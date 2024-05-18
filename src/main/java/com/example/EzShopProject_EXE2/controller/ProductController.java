package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
        @RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws DataNotFoundException {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(id, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }

    }

}
