package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.BlogDto;
import com.example.EzShopProject_EXE2.service.impl.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/guest/api/blogs")
@CrossOrigin
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(
            @RequestPart("blog") String blogJson,
            @RequestPart("imageFile") MultipartFile imageFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        BlogDto blogDto;
        try {
            blogDto = objectMapper.readValue(blogJson, BlogDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON format", e);
        }
        BlogDto createdBlog = blogService.createBlog(blogDto, imageFile);
        return ResponseEntity.ok(createdBlog);
    }
    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs();

        return ResponseEntity.ok(blogs);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(
            @PathVariable Long id,
            @RequestPart("blog") String blogJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        BlogDto blogDto;
        try {
            blogDto = objectMapper.readValue(blogJson, BlogDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON format", e);
        }
        BlogDto updatedBlog = blogService.updateBlog(id, blogDto, imageFile);
        return ResponseEntity.ok(updatedBlog);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        BlogDto blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }
}