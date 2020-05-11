package com.wolox.trainnerInMaven.controllers;

import com.wolox.trainnerInMaven.exceptions.BookIdMismatchException;
import com.wolox.trainnerInMaven.exceptions.BookNotFoundException;
import com.wolox.trainnerInMaven.models.Book;
import com.wolox.trainnerInMaven.repositories.BookRepository;
import com.wolox.trainnerInMaven.service.OpenLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents controller for books.
 *
 * @author German Asprino
 */

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OpenLibraryService openLibraryService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting:" + name;
    }

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book is not found", new Exception()));
    }

    @GetMapping("/title/{bookTitle}")
    public Book findByTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/author/{author}")
    public Optional<Book> findByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        bookRepository.findById(book.getId());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found", new Exception()));
        bookRepository.delete(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("That book does not match that id", new Exception());
        }
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book is not found", new Exception()));
        return bookRepository.save(book);
    }

    @GetMapping("/isbn/{isbn}")
    public Book findByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(bookRepository.save(new Book(openLibraryService.bookInfo(isbn))));
    }

    @GetMapping("/publisherAndGenreAndYear")
    public ArrayList<Book> findByPublisherAndGenreAndYear(@RequestParam(required = false) String publisher,
                                                          @RequestParam(required = false) String genre,
                                                          @RequestParam(required = false) String year) {
        return bookRepository.findByPublisherAndGenreAndYear(publisher, genre, year).get();
    }
}
