package com.example.EzShopProject_EXE2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDto {
    private Long id;

    @NotBlank
    private String name;
}
