package com.example.employee.Dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long star;
    private String comment;
    private Long bookId;
    private  String userName;
}
