package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Product;

import java.util.List;

public interface IProductService {
 Product getProductById(long id) throws Exception;
 ProductDto createProduct(ProductDto productDto) throws DataNotFoundException;
 Product updateProduct(Long id, Product product) throws Exception;
 List<Product> getAllProduct();
}
