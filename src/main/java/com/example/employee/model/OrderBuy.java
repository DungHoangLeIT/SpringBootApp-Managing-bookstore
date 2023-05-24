package com.example.employee.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_buy")
@Data
public class OrderBuy extends BaseEntity{

    @Column(name = "user_buy_id")
    private Long userBuyId;
    @Column(name = "book_id")
    private Long bookId;
    private Integer total;
}
