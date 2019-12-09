package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wolox.training.models.User;

/** Represents an repository of Users.
 * @author German Asprino
 */

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    public Optional<User> findAllByName(String name);
}