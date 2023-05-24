package com.example.employee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = " book")
public class Book extends BaseEntity {
    private String title;
    private String author;
    private Long categoryId;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "number_book")
    private Long numberBook;
    @Column(name = "number_sell")
    private Long numberSell;
    private String description;
    private String imageUrl;
}
