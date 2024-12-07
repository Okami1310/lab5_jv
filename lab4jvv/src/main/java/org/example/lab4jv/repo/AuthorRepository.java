package org.example.lab4jv.repo;

import org.example.lab4jv.data.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByAuthor(String author);
    Optional<Author> findByAuthorAndNameb(String author, String nameb);
}
