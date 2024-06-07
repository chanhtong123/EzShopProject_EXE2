package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.CategoryDto;
import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.dto.ShopDto;
import com.example.EzShopProject_EXE2.dto.TitleDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Category;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.model.Title;
import com.example.EzShopProject_EXE2.repository.CategoryRepository;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.repository.TitleRepository;
import com.example.EzShopProject_EXE2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TitleRepository titleRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, TitleRepository titleRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.titleRepository = titleRepository;
        this.shopRepository = shopRepository;
    }

//    @Override
//    public Product getProductById(long id) throws Exception {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()) {
//            return optionalProduct.get();
//        }
//        throw new DataNotFoundException("Cannot find product with id =" + id);
//    }

    @Override
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
    public ProductDto updateProduct(Long id, ProductDto productDto) throws Exception {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: " + id));

        // Cập nhật thông tin sản phẩm từ DTO
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setStatus(productDto.getStatus());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setWeight(productDto.getWeight());
        existingProduct.setSituation((productDto.getSituation()));
        existingProduct.setOverview(productDto.getOverview());
        existingProduct.setColor(productDto.getColor());
        existingProduct.setDetail(productDto.getDetail());

        // Cập nhật categories
        Set<Category> categories = new HashSet<>();
        for (CategoryDto categoryDto : productDto.getCategories()) {
            Category existingCategory = categoryRepository
                    .findById(categoryDto.getId())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find category with id: " + categoryDto.getId()));
            categories.add(existingCategory);
        }
        existingProduct.setCategories(categories);

        // Cập nhật title
        if (productDto.getTitle() != null) {
            Title existingTitle = titleRepository.findById(productDto.getTitle().getId())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find title with id: " + productDto.getTitle().getId()));
            existingProduct.setTitle(existingTitle);
        }

        // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductDto> searchProducts(String name, Double minPrice, Double maxPrice, String brand, Integer situation) {
        List<Product> products;

        if (name != null && brand != null && situation != null) {
            products = productRepository.findByNameContainingAndBrandAndSituation(name, brand, situation);


        } else if (name != null && brand != null) {
            products = productRepository.findByNameContainingAndBrand(name, brand);
        } else if (name != null && situation != null) {
            products = productRepository.findByNameContainingAndSituation(name, situation);


        } else if (brand != null && situation != null) {
            products = productRepository.findByBrandAndSituation(brand, situation);
        } else if (name != null) {
            products = productRepository.findByNameContaining(name);

        } else if (brand != null) {
            products = productRepository.findByBrand(brand);
        } else if (situation != null) {
            products = productRepository.findBySituation(situation);

        }  else if (name != null && minPrice != null && maxPrice != null && brand != null && situation != null) {
                products = productRepository.findByNameContainingAndPriceBetweenAndBrandAndSituation(name, minPrice, maxPrice, brand, situation);
            } else if (name != null && minPrice != null && maxPrice != null && situation != null) {
                products = productRepository.findByNameContainingAndPriceBetweenAndSituation(name, minPrice, maxPrice, situation);
            } else if (name != null && minPrice != null && maxPrice != null) {
                products = productRepository.findByNameContainingAndPriceBetween(name, minPrice, maxPrice);
            } else if (name != null && minPrice != null && brand != null) {
                products = productRepository.findByNameContainingAndPriceGreaterThanEqualAndBrand(name, minPrice, brand);
            } else if (name != null && minPrice != null && situation != null) {
                products = productRepository.findByNameContainingAndPriceGreaterThanEqualAndSituation(name, minPrice, situation);
            } else if (name != null && minPrice != null) {
                products = productRepository.findByNameContainingAndPriceGreaterThanEqual(name, minPrice);
            } else if (name != null && maxPrice != null && brand != null && situation != null) {
                products = productRepository.findByNameContainingAndPriceLessThanEqualAndBrandAndSituation(name, maxPrice, brand, situation);
            } else if (name != null && maxPrice != null && brand != null) {
                products = productRepository.findByNameContainingAndPriceLessThanEqualAndBrand(name, maxPrice, brand);
            } else if (name != null && maxPrice != null && situation != null) {
                products = productRepository.findByNameContainingAndPriceLessThanEqualAndSituation(name, maxPrice, situation);
            } else if (name != null && maxPrice != null) {
                products = productRepository.findByNameContainingAndPriceLessThanEqual(name, maxPrice);
            } else if (minPrice != null && maxPrice != null && brand != null && situation != null) {
                products = productRepository.findByPriceBetweenAndBrandAndSituation(minPrice, maxPrice, brand, situation);
            } else if (minPrice != null && maxPrice != null && brand != null) {
                products = productRepository.findByPriceBetweenAndBrand(minPrice, maxPrice, brand);
            } else if (minPrice != null && maxPrice != null && situation != null) {
                products = productRepository.findByPriceBetweenAndSituation(minPrice, maxPrice, situation);
            } else if (minPrice != null && maxPrice != null) {
                products = productRepository.findByPriceBetween(minPrice, maxPrice);
            } else {
                products = productRepository.findAll();
            }

            return products.stream().map(this::mapToDto).collect(Collectors.toList());
        }


    public List<ProductDto> getProductsByTitleId(Long titleId) {
        List<Product> products = productRepository.findByTitleId(titleId);
        return products.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public ProductDto getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return mapToDto(productOptional.get());
        } else {
            // Xử lý khi không tìm thấy sản phẩm (ví dụ: ném ngoại lệ)
            return null;
        }
    }

    @Override
    public List<Product> getAllProductsByShopId(Long shopId) {
        return productRepository.findByShopId(shopId);
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
        productDto.setSituation(product.getSituation());
        productDto.setOverview(product.getOverview());
        productDto.setColor(product.getColor());
        productDto.setDetail(product.getDetail());
        productDto.setSize(product.getSize());
        productDto.setCategories(product.getCategories().stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toSet()));

        if (product.getTitle() != null) {
            productDto.setTitle(mapToTitleDto(product.getTitle()));
        }
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
                .situation(productDto.getSituation())
                .overview(productDto.getOverview())
                .color(productDto.getColor())
                .detail(productDto.getDetail())
                .size(productDto.getSize())
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

        // Lấy thông tin về title
        if (productDto.getTitle() != null) {
            Title existingTitle = titleRepository.findById(productDto.getTitle().getId())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find title with id: " + productDto.getTitle().getId()));
            product.setTitle(existingTitle);
        }
        // Lấy thông tin về cửa hàng
        if (productDto.getShopId() != null) {
            Shop existingShop = shopRepository.findById(productDto.getShopId().getShopId())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Cannot find shop with id: " + productDto.getShopId().getShopId()));
            product.setShop(existingShop);
        }

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

    private TitleDto mapToTitleDto(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setName(title.getName());
        return titleDto;
    }
}
