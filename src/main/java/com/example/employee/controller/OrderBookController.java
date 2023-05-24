package com.example.employee.controller;

import com.example.employee.Dto.BuyDto;
import com.example.employee.Dto.ReviewDTO;
import com.example.employee.Util.ResponseWrapper;
import com.example.employee.service.BookService;
import com.example.employee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/api/order")
public class OrderBookController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    BookService bookService;

    @PostMapping("/review")
    public ResponseEntity<?> review(@RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.ok(reviewService.create(reviewDTO));
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyBook(@RequestBody BuyDto dto){
        return ResponseEntity.ok(reviewService.buyBook(dto));
    }
    @PostMapping("/view-order/{userName}")
    public ResponseEntity<?> view(@PathVariable(value = "userName") String userName){
        return ResponseEntity.ok(reviewService.viewOrder(userName));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper>delete(@PathVariable(value = "id") Long idBook){
        ResponseWrapper res = reviewService.deleteBook(idBook);
        return new ResponseEntity<>(res, res.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @PostMapping("get-review/{idBook}")
    public ResponseEntity<?> getView(@PathVariable(value = "idBook") Long  idBook){
        return ResponseEntity.ok(reviewService.getReview(idBook));
    }
}
