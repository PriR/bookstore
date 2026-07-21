package com.store.bookstore.controller;

import com.store.bookstore.dto.BookDTO;
import com.store.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> bookDTOs = bookService.getBooks();
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }
}
