package com.wolox.trainnerInMaven.controllers;

import com.wolox.trainnerInMaven.exceptions.BookNotFoundException;
import com.wolox.trainnerInMaven.exceptions.UserAlreadyExistException;
import com.wolox.trainnerInMaven.exceptions.UserIdMismatchException;
import com.wolox.trainnerInMaven.exceptions.UsersNotFoundException;
import com.wolox.trainnerInMaven.models.Book;
import com.wolox.trainnerInMaven.models.User;
import com.wolox.trainnerInMaven.repositories.BookRepository;
import com.wolox.trainnerInMaven.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/** Represents controller for User.
 * @author German Asprino
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException("User Not Found", new Exception()));
    }

    @ApiOperation(value = "Find any user by name", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = User.class, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, response = String.class, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/name/{name}")
    public Optional<User> findAllByName(
            @ApiParam(required = true, value = "User's name")
            @PathVariable String name) {
        return usersRepository.findFirstByUserName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/")
    public User create(@RequestBody User user) {
        if(usersRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistException("User already exist", new Exception());
        }
        return usersRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Throwable {
        final User user = usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException("User Not Found", new Exception()));
        usersRepository.delete(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        if (!id.equals(user.getId())) {
            throw new UserIdMismatchException("User id mismatch", new Exception());
        }
        return usersRepository.save(user);
    }
    @PutMapping("/{userId}/{bookId}")
    public void addBooks(@PathVariable Long userId, @PathVariable Long bookId) {
        final Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found", new Exception()));
        final User user = usersRepository.findById(userId).orElseThrow(() -> new UsersNotFoundException("User Not Found", new Exception()));
        user.addBook(book);
        usersRepository.save(user);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public void removeBook(@PathVariable Long userId, @PathVariable Long bookId) {
        final Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found", new Exception()));
        final User user = usersRepository.findById(userId).orElseThrow(() -> new UsersNotFoundException("User Not Found", new Exception()));
        user.removeBook(book);
        usersRepository.save(user);
    }
}
