package com.maskeddev.libraryapp.controller;

import com.maskeddev.libraryapp.entities.Book;
import com.maskeddev.libraryapp.service.BookService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook() throws Exception {
        return bookService.checkoutBook(null, null);
    }

    @GetMapping("/secure/isCheckedout/byUser")
    public boolean isBookCheckedout(){
        return bookService.isBookCheckedoutByUser(null, null);
    }

    @GetMapping("secure/checkout/count")
    public int getCheckoutCount(){
        return bookService.getCheckoutCount(null);
    }
}
