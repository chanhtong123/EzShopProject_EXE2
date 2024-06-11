package com.example.EzShopProject_EXE2.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EzShopProject_EXE2.dto.BlogDto;
import com.example.EzShopProject_EXE2.model.Blog;
import com.example.EzShopProject_EXE2.repository.BlogRepository;
import com.example.EzShopProject_EXE2.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public BlogDto createBlog(BlogDto blogDto, MultipartFile imageFile) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("url").toString();
            blogDto.setImage(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }

        Blog blog = mapToEntity(blogDto);
        Blog savedBlog = blogRepository.save(blog);
        return mapToDto(savedBlog);
    }
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto, MultipartFile imageFile) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            Blog existingBlog = optionalBlog.get();
            existingBlog.setName(blogDto.getName());

            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
                    String imageUrl = uploadResult.get("url").toString();
                    existingBlog.setImage(imageUrl);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload image", e);
                }
            }

            existingBlog.setDate(blogDto.getDate());
            existingBlog.setContent(blogDto.getContent());
            Blog updatedBlog = blogRepository.save(existingBlog);
            return mapToDto(updatedBlog);
        } else {
            throw new RuntimeException("Blog not found with id: " + id);
        }
    }
    public BlogDto getBlogById(Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            return mapToDto(optionalBlog.get());
        } else {
            // Handle the case where the blog entry does not exist.
            throw new RuntimeException("Blog not found with id: " + id);
        }
    }
    private Blog mapToEntity(BlogDto blogDto) {
        return Blog.builder()
                .id(blogDto.getId())
                .name(blogDto.getName())
                .image(blogDto.getImage())
                .date(blogDto.getDate())
                .content(blogDto.getContent())
                .build();
    }

    private BlogDto mapToDto(Blog blog) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(blog.getDate());

        return BlogDto.builder()
                .id(blog.getId())
                .name(blog.getName())
                .image(blog.getImage())
                .date(blog.getDate())
                .formattedDate(formattedDate) // Đặt giá trị này
                .content(blog.getContent())
                .build();
    }

}
