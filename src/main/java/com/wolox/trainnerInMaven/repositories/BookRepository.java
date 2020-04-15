package com.wolox.trainnerInMaven.repositories;

import com.wolox.trainnerInMaven.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Represents an repository of books.
 * @author German Asprino
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByAuthor(String author);

    public Book findByTitle(String title);
}
