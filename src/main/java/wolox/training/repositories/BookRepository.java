package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.Book;

/** Represents an repository of books.
 * @author German Asprino
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByAuthor(String author);

    public Book findByTitle(String title);
}
