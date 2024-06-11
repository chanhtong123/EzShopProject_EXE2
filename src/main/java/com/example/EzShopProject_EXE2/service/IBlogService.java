package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.BlogDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogService {
    BlogDto createBlog(BlogDto blogDto, MultipartFile imageFile);
    List<BlogDto> getAllBlogs();
    BlogDto getBlogById(Long id);
    BlogDto updateBlog(Long id, BlogDto blogDto, MultipartFile imageFile);
}
