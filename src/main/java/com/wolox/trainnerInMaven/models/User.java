package com.wolox.trainnerInMaven.models;


import com.google.common.base.Preconditions;
import com.wolox.trainnerInMaven.exceptions.BookAlreadyOwnedException;
import com.wolox.trainnerInMaven.exceptions.BookNotOwnedException;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** Represents a user model.
 * @author German Asprino
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(value = "Username for user", dataType = "String", example = "Hellfishg")
    @Column
    @NotBlank(message = "User Name is mandatory")
    private String userName;

    @ApiModelProperty(value = "Name of the user", dataType = "String", example = "German Asprino")
    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(value = "User birth date", dataType = "LocalDate", example = "1920-01-26")
    @Column
    @NotNull(message = "Birth date is mandatory")
    private LocalDate birthDate;

    @ApiModelProperty(value = "Books rents for this user", dataType = "List<Book>", example = "list of book")
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
        Preconditions.checkNotNull(name, "Name cannot be a null");

        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        Preconditions.checkNotNull(birthDate, "Birth date cannot be a null");

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
