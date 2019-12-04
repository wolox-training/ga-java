package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Represents an repository of books.
 * @author German Asprino
 */

public interface BookRepository extends JpaRepository<Optional, Long> {

    Optional findByAuthor(String author);
    public Book findByTitle(String title);
}