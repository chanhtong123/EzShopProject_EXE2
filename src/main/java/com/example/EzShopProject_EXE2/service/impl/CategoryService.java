package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.CategoryDto;
import com.example.EzShopProject_EXE2.model.Category;
import com.example.EzShopProject_EXE2.repository.CategoryRepository;
import com.example.EzShopProject_EXE2.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return mapToDto(savedCategory);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(categoryDto.getName());
            Category updatedCategory = categoryRepository.save(existingCategory);
            return mapToDto(updatedCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category item : categories) {
            CategoryDto dto = mapToDto(item);
            result.add(dto);
        }
        return result;
    }


    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        category.setId(categoryDto.getId());
        return category;
    }
}
