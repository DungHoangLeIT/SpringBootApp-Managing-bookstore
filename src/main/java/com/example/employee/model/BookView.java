package com.example.employee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_view")
@Data
public class BookView extends BaseEntity {
    @Column(name = "book_cover")
    private String bookCover;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "review_id")
    private Long reviewId;
}
