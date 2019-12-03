package wolox.training.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByAuthor(String author);
    public Book findByTitle(String title);
}