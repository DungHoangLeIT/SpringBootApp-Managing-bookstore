package com.example.employee.repository;


import com.example.employee.Dto.BookDto;
import com.example.employee.Dto.BookDtolist;
import com.example.employee.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = " Select b.id as id, b.imageUrl as imageUrl,  b.title as title, b.author as author, b.categoryId as categoryId, b.description as description, b.numberBook as numberBook, b.releaseDate as releaseDate from Book b ")
    List<BookDtolist> getAll();

    @Query(value = "  Select c.id, c.name from category c ", nativeQuery = true)
    List<Object> getAlllistCategory();
    @Query(value = "  Select b.* from book b where b.id =:idBook ", nativeQuery = true)
    Book findByIdd(Long idBook);
}
