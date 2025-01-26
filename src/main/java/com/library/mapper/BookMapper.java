package com.library.mapper;

import com.library.dto.BookDto;
import com.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toDomain(BookDto bookDto);

    List<BookDto> toDto(List<Book> books);

    BookDto toDto(Book book);
}

