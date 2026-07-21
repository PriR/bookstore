package com.store.bookstore.service.impl;

import com.store.bookstore.dto.AuthorDTO;
import com.store.bookstore.dto.BookDTO;
import com.store.bookstore.entities.Book;
import com.store.bookstore.repository.BookRepository;
import com.store.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> getBooks() {
        List<Book> books = this.bookRepository.findAll();

        return books
                .stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getPrice(),
                        new AuthorDTO(
                                book.getAuthor().getId(),
                                book.getAuthor().getFirstName(),
                                book.getAuthor().getLastName()
                        )))
                .toList();
    }
}
