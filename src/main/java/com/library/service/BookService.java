package com.library.service;

import com.library.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthor(Long authorId);

    BookDto saveBook(BookDto book);

    void deleteBook(Long id);
}
