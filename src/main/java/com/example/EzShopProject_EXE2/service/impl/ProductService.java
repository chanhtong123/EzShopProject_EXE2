package com.example.EzShopProject_EXE2.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EzShopProject_EXE2.dto.CategoryDto;
import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Category;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.repository.CategoryRepository;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public ProductDto createProduct(ProductDto productDto, MultipartFile[] imageFiles,  Long shopId) throws DataNotFoundException {
        try {
            // Assuming imageFiles is an array and should be uploaded in sequence
            if (imageFiles.length > 0) {
                Map uploadResult1 = cloudinary.uploader().upload(imageFiles[0].getBytes(), ObjectUtils.emptyMap());
                productDto.setImage(uploadResult1.get("url").toString());
            }
            if (imageFiles.length > 1) {
                Map uploadResult2 = cloudinary.uploader().upload(imageFiles[1].getBytes(), ObjectUtils.emptyMap());
                productDto.setImage2(uploadResult2.get("url").toString());
            }
            if (imageFiles.length > 2) {
                Map uploadResult3 = cloudinary.uploader().upload(imageFiles[2].getBytes(), ObjectUtils.emptyMap());
                productDto.setImage3(uploadResult3.get("url").toString());
            }
            if (imageFiles.length > 3) {
                Map uploadResult4 = cloudinary.uploader().upload(imageFiles[3].getBytes(), ObjectUtils.emptyMap());
                productDto.setImage4(uploadResult4.get("url").toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
//        List<Shop> shops = shopRepository.findByOwnerId(shopId);
//        if (shops.isEmpty()) {
//            throw new DataNotFoundException("Cannot find shop with owner id: " + shopId);
//        }
//        Shop shop = shops.get(0); // Assuming one shop per owner

        Product product = mapToEntity(productDto,shopId);
//        product.setShop(shop);
        // Generate product code if not present
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

    @Override
    public List<ProductDto> getProductsByShopId(Long shopId) {
        List<Product> products = productRepository.findByShopId(shopId);
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
        productDto.setImage(product.getImage());
        productDto.setDescription(product.getDescription());
        productDto.setCode(product.getCode());
        productDto.setStatus(product.getStatus());
        productDto.setBrand(product.getBrand());
        productDto.setWeight(product.getWeight());
        productDto.setSituation(product.getSituation());
        productDto.setOverview(product.getOverview());
        productDto.setColor(product.getColor());
        productDto.setDetail(product.getDetail());
        productDto.setSize(product.getSize());
        productDto.setImage2(product.getImage2());
        productDto.setImage3(product.getImage3());
        productDto.setImage4(product.getImage4());
        productDto.setCategories(product.getCategories().stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toSet()));
        productDto.setShopId(product.getShop().getId());


        // Add mappings for shop and orderDetails if necessary
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto,Long shopId) throws DataNotFoundException {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .code(productDto.getCode())
                .status(productDto.getStatus())
                .brand(productDto.getBrand())
                .weight(productDto.getWeight())
                .situation(productDto.getSituation())
                .overview(productDto.getOverview())
                .color(productDto.getColor())
                .detail(productDto.getDetail())
                .size(productDto.getSize())
                .image2(productDto.getImage2())
                .image3(productDto.getImage3())
                .image4(productDto.getImage4())
                .image(productDto.getImage())
                .build();
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new DataNotFoundException("Shop not found with id: " + shopId));

        // Liên kết Product với Shop
        product.setShop(shop);
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
        // Tạo 3 chữ cái ngẫu nhiên viết hoa
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            char randomChar = letters.charAt(random.nextInt(letters.length()));
            sb.append(randomChar);
        }

        // Tạo 4 số ngẫu nhiên
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên từ 0 đến 9999

        // Format kết quả và trả về
        return sb.toString() + String.format("%04d", randomNumber);
    }


    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }


}