package com.library.service.impl;

import com.library.configuration.KafkaProperties;
import com.library.dto.AuthorDto;
import com.library.mapper.AuthorMapper;
import com.library.repository.AuthorRepository;
import com.library.service.AuthorService;
import com.library.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaProperties kafkaProperties;

    @Override
    public List<AuthorDto> getAllAuthors() {
        var authors = authorRepository.findAll();

        String message = "get-all-authors";
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), message);

        return authorMapper.toDto(authors);
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        String message = "get-author-by-id-" + id;
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), message);

        return authorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found")));
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto author) {
        var entity = authorMapper.toDomain(author);

        String messageCreate = "create-author-" + entity.getId() + "-" + entity.getName();
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageCreate);

        var savedData = authorRepository.save(entity);
        return authorMapper.toDto(savedData);
    }

    @Override
    public void deleteAuthor(Long id) {
        String messageDelete = "delete-author-" + id;
        kafkaProducerService.sendMessage(kafkaProperties.getProducer().getTopic(), messageDelete);
        authorRepository.deleteById(id);
    }

}
