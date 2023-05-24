package com.example.employee.service.Impl;

import com.example.employee.Dto.AllReview;
import com.example.employee.Dto.BuyDto;
import com.example.employee.Dto.OrderBookDto;
import com.example.employee.Dto.ReviewDTO;
import com.example.employee.Util.ResponseWrapper;
import com.example.employee.Util.ValidateUtil;
import com.example.employee.model.*;
import com.example.employee.repository.*;
import com.example.employee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BookViewRepository bookViewRepository;
    @Autowired
    UserRepository  userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderBookRepository orderBookRepository;
    @Transactional
    @Override
    public Object create(ReviewDTO reviewDTO) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//       User user = (User) auth;
//        if(user == null) throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.USER_NOT_FOUND);
        User user = userRepository.findByUsername(reviewDTO.getUserName());
        Book bookView = bookRepository.findById(reviewDTO.getBookId()).orElse(null);
        Review review = new Review();
        review.setStar(reviewDTO.getStar());
        review.setComment(reviewDTO.getComment());
        review.setBookId(bookView.getId());
        review.setUserId(user.getId());
        Review reviewSave = reviewRepository.save(review);
        BookView bookView1 = new BookView();
        bookView1.setAuthorName(bookView.getAuthor());
        bookView1.setBookTitle(bookView.getTitle());
        bookView1.setReviewId(reviewSave.getId());
        bookViewRepository.save(bookView1);
        return review;
    }

    @Override
    public Object buyBook(BuyDto dto) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Assert.notNull(user, ValidateUtil.MESSAGE_CODE.USER_NOT_FOUND);
        User user = userRepository.findByUsername(dto.getUserName());
        Book book = bookRepository.findByIdd(dto.getId());
        if (book== null){
            throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.BOOK_NOT_FOUND);
        }
        OrderBuy orderBuy = new OrderBuy();
        orderBuy.setBookId(dto.getId());
        orderBuy.setTotal(dto.getTotal());
        orderBuy.setUserBuyId(user.getId());
        orderBookRepository.save(orderBuy);
        return orderBuy;
    }

    @Override
    public Object viewOrder(String userName) {
//        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Assert.notNull(user, ValidateUtil.MESSAGE_CODE.USER_NOT_FOUND);
        User user = userRepository.findByUsername(userName);
        List<OrderBookDto> list = new ArrayList<>();
        List<OrderBuy> orderBuy = orderBookRepository.findByUserBuyId(user.getId());
        for (OrderBuy orderBuy1 : orderBuy) {
            Book book = bookRepository.findById(orderBuy1.getBookId()).orElse(null);
            OrderBookDto bookDto = new OrderBookDto();
            bookDto.setOrderId(orderBuy1.getId());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setDescription(book.getDescription());
            bookDto.setTitle(book.getTitle());
            bookDto.setCategoryId(book.getCategoryId());
            bookDto.setImageUrl(book.getImageUrl());
            bookDto.setReleaseDate(book.getReleaseDate());
            bookDto.setNumberBook(book.getNumberBook());
            bookDto.setTotal(orderBuy1.getTotal());
            bookDto.setId(book.getId());
            list.add(bookDto);
        }
        return list;
    }

    @Override
    public ResponseWrapper deleteBook(Long idBook) {
        orderBookRepository.deleteById(idBook);
        return new ResponseWrapper(idBook);
    }

    @Override
    public Object getReview(Long idBook) {
        List<AllReview> list = reviewRepository.findByBookId(idBook);
        return list.listIterator();
    }
}
