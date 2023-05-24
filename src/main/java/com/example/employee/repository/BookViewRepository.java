package com.example.employee.repository;

import com.example.employee.model.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookViewRepository extends JpaRepository<BookView, Long> {
    @Query(value = "select * from  book_view b left join review r on r.id = b.review_id " , nativeQuery = true)
    BookView getAll();

    Optional<BookView> findByBookTitle(String bookTitle);
}
