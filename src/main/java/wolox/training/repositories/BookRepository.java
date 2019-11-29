package wolox.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String author);
}
