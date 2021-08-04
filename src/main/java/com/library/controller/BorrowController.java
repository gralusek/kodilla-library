package com.library.controller;

import com.library.Dto.BookDto;
import com.library.Dto.BorrowDto;
import com.library.domain.Book;
import com.library.domain.Borrow;
import com.library.mapper.BookMapper;
import com.library.mapper.BorrowMapper;
import com.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {
    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    public BorrowController(BorrowService borrowService, BorrowMapper borrowMapper) {
        this.borrowService = borrowService;
        this.borrowMapper = borrowMapper;
    }

    @PostMapping(value = "borrowBook")
    public void borrowBook(@RequestParam Long bookId, @RequestParam Long readerId) {
        borrowService.borrow(bookId, readerId);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long borrowId) {
        borrowService.returnBook(borrowId);
    }

    @GetMapping(value = "getBorrow")
    public BorrowDto getBorrow(@RequestParam Long id) {
        return borrowMapper.mapToBorrowDto(
                borrowService.findById(id).orElseThrow(() -> new RuntimeException("Borrow with given ID was not found")));
    }

    @GetMapping(value = "getBorrows")
    public List<BorrowDto> getBorrows() {
        List<Borrow> borrows = borrowService.findAll();
        return borrowMapper.mapToBorrowDtoList(borrows);
    }

    @DeleteMapping(value = "deleteById")
    public void deleteById(@RequestParam Long id) {
        borrowService.deleteById(id);
    }
}
