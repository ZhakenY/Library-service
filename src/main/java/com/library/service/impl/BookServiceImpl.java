package com.library.service.impl;

import com.library.configuration.KafkaProperties;
import com.library.dto.BookDto;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import com.library.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaProperties kafkaProperties;

    @Override
    public List<BookDto> getAllBooks() {
        String messageGetAllBooks = "get-all-books";
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageGetAllBooks);

        return bookMapper.toDto(bookRepository.findAll());
    }

    @Override
    public List<BookDto> getBooksByAuthor(Long authorId) {
        String messageGetBooksByAuthor = "get-books-by-author-" + authorId;
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageGetBooksByAuthor);

        return bookMapper.toDto(bookRepository.findByAuthorId(authorId));
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        var savedData = bookRepository.save(bookMapper.toDomain(bookDto));

        String messageCreate = "create-book-" + savedData.getId() + "-" + savedData.getTitle();
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageCreate);

        return bookMapper.toDto(savedData);
    }

    @Override
    public void deleteBook(Long id) {
        String messageDeleteBook = "delete-book-" + id;
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageDeleteBook);

        bookRepository.deleteById(id);
    }
}
