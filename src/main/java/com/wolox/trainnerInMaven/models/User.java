package com.wolox.trainnerInMaven.models;

import com.wolox.trainnerInMaven.exceptions.BookAlreadyOwnedException;
import com.wolox.trainnerInMaven.exceptions.BookNotOwnedException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Represents a user model.
 * @author German Asprino
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "User Name is mandatory")
    private String userName;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column
    @NotNull(message = "Birth date is mandatory")
    private LocalDate birthDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_user",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<Book> books = new ArrayList<Book>();

    // Getters and setters:

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addBook(Book book){
        if (book == null){ throw new NullPointerException("Book cannot be a null");}
        if (this.books.contains(book)){ throw new BookAlreadyOwnedException("Book Already Owned", new Exception()); }
        this.books.add(book);
    }

    public void removeBook(Book book){
        if (book == null){ throw new NullPointerException("Book cannot be a null");}
        if ( !this.books.contains(book) ){ throw new BookNotOwnedException("Book Not Owned By This User", new Exception()); }
        this.books.remove(book);
    }
}
