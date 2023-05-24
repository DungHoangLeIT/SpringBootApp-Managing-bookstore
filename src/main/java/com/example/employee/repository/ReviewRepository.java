package com.example.employee.repository;

import com.example.employee.Dto.AllReview;
import com.example.employee.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "Select r.comment as comment , r.star as star ,s.username as userName   from Review r left join User s on s.id = r.userId where r.bookId =:idBook " )
    List<AllReview> findByBookId(Long idBook);
}
