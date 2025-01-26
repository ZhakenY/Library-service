CREATE TABLE authors (
       id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
       id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author_id INT NOT NULL,
       CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);
