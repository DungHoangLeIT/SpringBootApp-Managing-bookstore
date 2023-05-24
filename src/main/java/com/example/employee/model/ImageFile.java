package com.example.employee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image_file")
@Data
@NoArgsConstructor
public class ImageFile extends BaseEntity{
  private String filePath;
  private String fileType;
}
