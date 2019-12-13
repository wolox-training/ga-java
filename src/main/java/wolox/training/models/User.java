package wolox.training.models;

import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.validation.constraints.NotBlank;
import wolox.training.exceptions.BookAlreadyOwnedException;
import wolox.training.exceptions.BookNotOwnedException;

/** Represents a user model.
 * @author German Asprino
 */

@Entity
@ApiModel(description = "User for wolox training java")
@Table(name = "Users")
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
    @NotBlank(message = "Birth date is mandatory")
    private LocalDate birthDate;

    @ApiModelProperty(value = "Books rents for this user", dataType = "List<Book>", example = "list of book")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_user",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<Book> books = new ArrayList<Book>();

    public User(){}

    // Getters and setters:

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        Preconditions.checkNotNull(name, "UserName cannot be a null");
        this.userName = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Preconditions.checkNotNull(name, "Name cannot be a null");

        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        Preconditions.checkNotNull(birthDate, "Birth date cannot be a null");

        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(this.books);
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
