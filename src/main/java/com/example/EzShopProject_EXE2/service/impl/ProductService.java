package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    @Override
    public Product getProductById(long id) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw new DataNotFoundException("Cannot find product with id =" + id);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
