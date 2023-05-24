package com.example.employee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "review")
@Data
public class Review extends BaseEntity{
    private Long star;
    private String comment;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "user_buy_id")
    private Long userId;
}
