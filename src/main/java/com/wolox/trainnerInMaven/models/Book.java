package com.wolox.trainnerInMaven.models;


import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import com.wolox.trainnerInMaven.exceptions.BookAlreadyOwnedException;
import com.wolox.trainnerInMaven.exceptions.BookNotOwnedException;

/** Represents a book.
 * @author German Asprino
 */

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @Column
    @NotBlank(message = "Author is mandatory")
    private String author;

    @Column
    @NotBlank(message = "Image is mandatory")
    private String image;

    @Column
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Column
    @NotBlank(message = "Subtitle is mandatory")
    private String subtitle;

    @Column
    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    @Column
    @NotBlank(message = "Year is mandatory")
    private String year;

    @Column
    @NotBlank(message = "Pages is mandatory")
    private String pages;

    @Column
    @NotBlank(message = "Isbn is mandatory")
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private List<User> users = new ArrayList<>();

    public Book(){}

    // Getters and setters:

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        Preconditions.checkNotNull(genre, "Genre cannot be a null");
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        Preconditions.checkNotNull(author, "Author cannot be a null");

        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        Preconditions.checkNotNull(image, "Image cannot be a null");

        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Preconditions.checkNotNull(title, "Title cannot be a null");

        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        Preconditions.checkNotNull(subtitle, "Subtitle cannot be a null");

        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        Preconditions.checkNotNull(publisher, "Publisher cannot be a null");
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        Preconditions.checkNotNull(year, "Year cannot be a null");
        this.year = year;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        Preconditions.checkNotNull(pages, "Pages cannot be a null");
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        Preconditions.checkNotNull(isbn, "Isbn cannot be a null");
        this.isbn = isbn;
    }

    public void addUsers(User user) {
        if (user == null){ throw new NullPointerException("User Cannot Be A Null");}
        if (this.users.contains(user)){ throw new BookAlreadyOwnedException("User Already Owned this Book", new Exception()); }
        this.users.add(user);
    }

    public void removeBook(User user) {
        if (user == null){ throw new NullPointerException("User Cannot Be A Null");}
        if (!this.users.contains(user)){ throw new BookNotOwnedException("Book Not Owned By This User", new Exception()); }
        this.users.remove(user);
    }
}
