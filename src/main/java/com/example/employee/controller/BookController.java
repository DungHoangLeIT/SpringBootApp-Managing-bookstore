package com.example.employee.controller;

import com.example.employee.Dto.BookDto;
import com.example.employee.Util.ResponseWrapper;
import com.example.employee.service.BookService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/home")
    public ResponseEntity<?>list(){
        return  ResponseEntity.ok(bookService.list());
    }
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody BookDto dto){
        return  ResponseEntity.ok(bookService.create(dto));
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<?>edit(@PathVariable(value = "id") Long id, @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.edit(id, dto));
    }
    @GetMapping("/view/{idBook}")
    public ResponseEntity<?>view(@PathVariable(value = "idBook")  Long idBook){
        return  ResponseEntity.ok(bookService.view(idBook));
    }
    @DeleteMapping("/{idBook}")
    public ResponseEntity<ResponseWrapper>delete(@PathVariable(value = "idBook") Long idBook){
        ResponseWrapper res = bookService.delete(idBook);
        return new ResponseEntity<>(res, res.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/list-category")
    public ResponseEntity<?>listCategory(){
        return  ResponseEntity.ok(bookService.listCategory());
    }
}
