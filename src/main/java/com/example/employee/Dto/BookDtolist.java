package com.example.employee.Dto;

import lombok.Data;

public interface BookDtolist {
    Long getId();
    String getTitle();
    String getAuthor();
    String getDescription();
    Long getCategoryId();
    String getReleaseDate();
    String getImageUrl();
    Long getNumberBook();

}
