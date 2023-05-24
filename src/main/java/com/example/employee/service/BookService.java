package com.example.employee.service;


import com.example.employee.Dto.BookDto;
import com.example.employee.Dto.BookDtolist;
import com.example.employee.Util.ResponseWrapper;
import com.example.employee.model.Book;

import java.util.List;

public interface BookService {
    List<BookDtolist> list();

    Object create(BookDto dto);

     Book view(Long idBook);

    ResponseWrapper delete(Long idBook);

    List<Object> listCategory();

    Object edit(Long id, BookDto dto);

}
