package com.library.controller;

import com.library.dto.BookDto;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
