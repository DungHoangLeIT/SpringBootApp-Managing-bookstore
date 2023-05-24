package com.example.employee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category")
public class Category extends BaseEntity {
    private String name;
}
