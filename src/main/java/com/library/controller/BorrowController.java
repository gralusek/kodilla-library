package com.library.controller;

import com.library.mapper.BorrowMapper;
import com.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public void borrowBook(@RequestParam Long bookId, @RequestParam Long readerId) {
        borrowService.borrow(bookId, readerId);
    }

    @PutMapping
    public void returnBook(@RequestParam Long borrowId) {
        borrowService.returnBook(borrowId);
    }
}
