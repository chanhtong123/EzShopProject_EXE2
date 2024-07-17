package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
 // Product getProductById(long id) throws Exception;
 ProductDto createProduct(ProductDto productDto, MultipartFile[] imageFiles,  Long shopId) throws DataNotFoundException;
 ProductDto updateProduct(Long id, ProductDto productDto) throws Exception;
 List<ProductDto> getAllProduct();
 List<ProductDto> searchProducts(String name, Double minPrice, Double maxPrice, String brand, Integer situation);
 ProductDto getProductById(Long id);

 List<Product> getAllProductsByShopId(Long shopId);
 List<ProductDto> getProductsByShopId(Long shopId);
}