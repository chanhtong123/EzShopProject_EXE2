package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.CategoryDto;
import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Category;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.repository.CategoryRepository;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(long id) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw new DataNotFoundException("Cannot find product with id =" + id);
    }

    public ProductDto createProduct(ProductDto productDto) throws DataNotFoundException {
        Product product = mapToEntity(productDto);

        // Tạo mã code cho sản phẩm nếu chưa có
        if (product.getCode() == null || product.getCode().isEmpty()) {
            product.setCode(generateProductCode());
        }

        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCode(product.getCode());
        productDto.setStatus(product.getStatus());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setBrand(product.getBrand());
        productDto.setWeight(product.getWeight());
        productDto.setCategories(product.getCategories().stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toSet()));
        // Add mappings for shop and orderDetails if necessary
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) throws DataNotFoundException {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .code(productDto.getCode())
                .status(productDto.getStatus())
                .quantity(productDto.getQuantity())
                .category(productDto.getCategory())
                .brand(productDto.getBrand())
                .weight(productDto.getWeight())
                .build();

        // Lấy thông tin về category
        Set<Category> categories = new HashSet<>();
        for (CategoryDto categoryDto : productDto.getCategories()) {
            Category existingCategory = categoryRepository
                    .findById(categoryDto.getId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "Cannot find category with id: " + categoryDto.getId()));
            categories.add(existingCategory);
        }
        product.setCategories(categories);

        // Add mappings for shop and orderDetails if necessary
        return product;
    }

    private String generateProductCode() {
        // Sử dụng UUID để tạo mã code duy nhất cho sản phẩm
        return "PRD-" + UUID.randomUUID().toString();
    }

    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}

