package com.library.mapper;

import com.library.dto.AuthorDto;
import com.library.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    Author toDomain(AuthorDto authorDto);

    List<AuthorDto> toDto(List<Author> authors);

    AuthorDto toDto(Author author);
}

