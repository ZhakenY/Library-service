package com.library.service;

import com.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(Long authorId);

    AuthorDto saveAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);

}
