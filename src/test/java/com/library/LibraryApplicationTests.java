package com.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.AuthorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryApplicationTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private String json;
    private AuthorDto authorDto;

    @BeforeEach
    public void setUp() throws Exception {
        authorDto = new AuthorDto(1L, "Test Author");
        json = objectMapper.writeValueAsString(authorDto);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testAuthorDtoDeserialization() throws Exception {
        assertNotNull(json);
        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"name\":\"Test Author\""));
    }

    @Test
    public void testAuthorDtoEmpty() throws Exception {
        String emptyJson = "{}";

        AuthorDto author = objectMapper.readValue(emptyJson, AuthorDto.class);

        assertNotNull(author);
        assertNull(author.getId());
        assertNull(author.getName());
    }
}
