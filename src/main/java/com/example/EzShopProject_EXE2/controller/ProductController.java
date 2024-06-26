package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;

import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.response.ProductResponse;
import com.example.EzShopProject_EXE2.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/guest/api/products")
@CrossOrigin
public class ProductController {

    private final IProductService productService;


    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
//        try {
//            Product product = productService.getProductById(id);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @PostMapping
//    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws DataNotFoundException {
//        ProductDto createdProduct = productService.createProduct(productDto);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestPart("product") String productJson,
            @RequestPart("imageFiles") MultipartFile[] imageFiles) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto;
        try {
            productDto = objectMapper.readValue(productJson, ProductDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON format", e);
        }
        ProductDto createdProduct = productService.createProduct(productDto, imageFiles);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(id, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProductsInPriceRange(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer situation) {
        List<ProductDto> products = productService.searchProducts(name, minPrice, maxPrice, brand, situation);
        return ResponseEntity.ok(products);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/products/shop/{shopId}")
    public ResponseEntity<List<ProductResponse>> getProductsByShopId(@PathVariable Long shopId) {
        List<Product> products = productService.getAllProductsByShopId(shopId);
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : products){
            responseList.add(ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .code(product.getDescription())
                    .status(product.getStatus())
                    .brand(product.getBrand())
                    .weight(product.getWeight())
                    .situation(product.getSituation())
                    .color(product.getColor())
                    .overview(product.getOverview())
                    .image(product.getImage())
                    .build());
        }

        return ResponseEntity.ok(responseList);
    }
}