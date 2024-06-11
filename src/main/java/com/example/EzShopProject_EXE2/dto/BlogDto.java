    package com.example.EzShopProject_EXE2.dto;

    import lombok.*;

    import java.util.Date;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class BlogDto {
        private Long id;
        private String name;
        private String image;
        private Date date;
        private String content;
        private String formattedDate; // Thêm trường này
    }
