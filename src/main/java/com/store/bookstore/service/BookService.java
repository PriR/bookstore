package com.store.bookstore.service;

import com.store.bookstore.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();
}
