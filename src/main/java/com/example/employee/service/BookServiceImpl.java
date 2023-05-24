package com.example.employee.service;

import com.example.employee.Dto.BookDto;
import com.example.employee.Dto.BookDtolist;
import com.example.employee.Util.ResponseWrapper;
import com.example.employee.Util.ValidateUtil;
import com.example.employee.model.Book;
import com.example.employee.model.User;
import com.example.employee.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookDtolist> list() {
        return bookRepository.getAll();
    }

    @Override
    public Object create(BookDto dto) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.print(user.getUsername());
            Book book = new Book();
            book.setNumberBook(dto.getNumberBook());
            book.setCategoryId(dto.getCategoryId());
            book.setAuthor(dto.getAuthor());
            book.setTitle(dto.getTitle());
            book.setReleaseDate(dto.getReleaseDate());
            book.setDescription(dto.getDescription());
            book.setImageUrl(dto.getImageUrl());
            bookRepository.save(book);
            return book;
    }

    @Override
    public Book view(Long idBook) {
         return bookRepository.findByIdd(idBook);
    }

    @Override
    public ResponseWrapper delete(Long idBook) {
        bookRepository.deleteById(idBook);
        return new ResponseWrapper(idBook);
    }

    @Override
    public List<Object> listCategory() {
        return bookRepository.getAlllistCategory();
    }

    @Transactional
    @Override
    public Object edit(Long id, BookDto dto) {
        Book book = bookRepository.findByIdd(id);
        if (book== null){
            throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.BOOK_NOT_FOUND);
        }
        book.setImageUrl(dto.getImageUrl());
        book.setNumberBook(dto.getNumberBook());
        book.setDescription(dto.getDescription());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategoryId(dto.getCategoryId());
        book.setReleaseDate(dto.getReleaseDate());
        bookRepository.save(book);
        return book;
    }
}
