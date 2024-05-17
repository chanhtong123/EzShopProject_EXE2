package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.CategoryDto;
import com.example.EzShopProject_EXE2.model.Category;

import java.util.List;

public interface ICategoryService {
    CategoryDto createCategory (CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
}
