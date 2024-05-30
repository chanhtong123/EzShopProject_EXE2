package com.example.EzShopProject_EXE2.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDto {
    private Long id;

    @NotBlank(message = "Voucher code cannot be blank")
    @Size(max = 50, message = "Voucher code must be less than 50 characters")
    private String code;

    @NotBlank(message = "Voucher name cannot be blank")
    @Size(max = 100, message = "Voucher name must be less than 100 characters")
    private String name;

    @Positive(message = "Voucher value must be positive")
    private double value;

    @NotNull(message = "Creation date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creatDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
