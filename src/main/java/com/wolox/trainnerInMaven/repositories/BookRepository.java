package com.wolox.trainnerInMaven.repositories;

import com.wolox.trainnerInMaven.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

/** Represents an repository of books.
 * @author German Asprino
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByAuthor(String author);

    public Book findByTitle(String title);

    public Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (:publisher IS NULL OR b.publisher LIKE %:publisher)"
            + " AND (:genre IS NULL OR b.genre LIKE %:genre%)"
            + " AND (:year IS NULL OR b.year = :year)")
    public Optional<ArrayList<Book>> findByPublisherAndGenreAndYear(@Param("publisher") String publisher,
                                                              @Param("genre") String genre,
                                                              @Param("year") String year);
}
