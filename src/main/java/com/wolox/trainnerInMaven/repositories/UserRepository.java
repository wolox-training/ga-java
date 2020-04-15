package com.wolox.trainnerInMaven.repositories;

import com.wolox.trainnerInMaven.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/** Represents an repository of Users.
 * @author German Asprino
 */

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findFirstByUserName(String userName);
}
