package com.example.employee.Dto;

import lombok.Data;

@Data
public class OrderBookDto {
    private Long orderId;
    private String title;
    private String author;
    private String description;
    private Long categoryId;
    private String releaseDate;
    private Long numberBook;
    private String imageUrl;
    private int total;
    private Long id;
}
