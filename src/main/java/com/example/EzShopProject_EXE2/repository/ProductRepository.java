package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductsById(Long productId);
    List<Product> findByShopId(Long shopId);
    List<Product> findByNameContainingAndPriceAndBrand(String name, Double price, String brand);
    List<Product> findByNameContaining(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByBrand(String brand);
    List<Product> findBySituation(int situation);
    List<Product> findByNameContainingAndSituation(String name, int situation);
    List<Product> findByPriceAndSituation(Double price, int situation);
    List<Product> findByBrandAndSituation(String brand, int situation);
    List<Product> findByNameContainingAndPriceAndSituation(String name, Double price, int situation);
    List<Product> findByNameContainingAndBrandAndSituation(String name, String brand, int situation);
    List<Product> findByPriceAndBrandAndSituation(Double price, String brand, int situation);
    List<Product> findByNameContainingAndPriceAndBrandAndSituation(String name, Double price, String brand, int situation);
    List<Product> findByPriceAndBrand(Double price, String brand);
    List<Product> findByNameContainingAndBrand(String name, String brand);
    List<Product> findByNameContainingAndPrice(String name, Double price);
    List<Product> findByNameContainingAndPriceBetweenAndBrandAndSituation(String name, Double minPrice, Double maxPrice, String brand, Integer situation);
    List<Product> findByNameContainingAndPriceBetweenAndBrand(String name, Double minPrice, Double maxPrice, String brand);
    List<Product> findByNameContainingAndPriceBetweenAndSituation(String name, Double minPrice, Double maxPrice, Integer situation);
    List<Product> findByNameContainingAndPriceBetween(String name, Double minPrice, Double maxPrice);
    List<Product> findByNameContainingAndPriceGreaterThanEqualAndBrandAndSituation(String name, Double minPrice, String brand, Integer situation);
    List<Product> findByNameContainingAndPriceGreaterThanEqualAndBrand(String name, Double minPrice, String brand);
    List<Product> findByNameContainingAndPriceGreaterThanEqualAndSituation(String name, Double minPrice, Integer situation);
    List<Product> findByNameContainingAndPriceGreaterThanEqual(String name, Double minPrice);
    List<Product> findByNameContainingAndPriceLessThanEqualAndBrandAndSituation(String name, Double maxPrice, String brand, Integer situation);
    List<Product> findByNameContainingAndPriceLessThanEqualAndBrand(String name, Double maxPrice, String brand);
    List<Product> findByNameContainingAndPriceLessThanEqualAndSituation(String name, Double maxPrice, Integer situation);
    List<Product> findByNameContainingAndPriceLessThanEqual(String name, Double maxPrice);
    List<Product> findByPriceBetweenAndBrandAndSituation(Double minPrice, Double maxPrice, String brand, Integer situation);
    List<Product> findByPriceBetweenAndBrand(Double minPrice, Double maxPrice, String brand);
    List<Product> findByPriceBetweenAndSituation(Double minPrice, Double maxPrice, Integer situation);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);



}
