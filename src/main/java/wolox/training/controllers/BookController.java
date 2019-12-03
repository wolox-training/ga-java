package wolox.training.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

/** Represents controller for books.
 * @author German Asprino
 */

@RestController
@RequestMapping("/woloxTraining/books")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping("/greeting")
  public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    model.addAttribute("name", name);
    return "greeting";
  }

  @GetMapping
  public Iterable findAll() {
    return bookRepository.findAll();
  }

  @GetMapping("/{ID}")
  public Book findOne(@PathVariable Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book is not found", new Exception()));
  }

  @GetMapping("/title/{bookTitle}")
  public Book findByTitle(@PathVariable String title) {
    return bookRepository.findByTitle(title);
  }

  @GetMapping("/author/{author}")
  public List<Book> findByAuthor(@PathVariable String author) {
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
    bookRepository.findById(id).orElseThrow(() -> new BookIdMismatchException("Book is not found", new Exception()));
    return bookRepository.save(book);
  }
}
