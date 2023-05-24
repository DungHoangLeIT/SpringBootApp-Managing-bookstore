package com.example.employee.service;

import com.example.employee.Dto.BuyDto;
import com.example.employee.Dto.ReviewDTO;
import com.example.employee.Util.ResponseWrapper;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Object create(ReviewDTO reviewDTO);

    Object buyBook(BuyDto dto);

    Object viewOrder(String userName);

    ResponseWrapper deleteBook(Long idBook);

    Object getReview(Long idBook);
}
