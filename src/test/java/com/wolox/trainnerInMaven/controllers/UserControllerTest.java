package com.wolox.trainnerInMaven.controllers;

import com.wolox.trainnerInMaven.models.Book;
import com.wolox.trainnerInMaven.models.User;
import com.wolox.trainnerInMaven.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository mockUserRepository;

    @MockBean
    private User oneTestUser;
    private Book oneTestBook;



    @Before
    public void setUp() {
        oneTestBook = new Book();
        oneTestUser = new User();

        oneTestBook.setAuthor("Lovecrath");
        oneTestBook.setGenre("Terror");
        oneTestBook.setImage("image.png");
        oneTestBook.setIsbn("552-0458");
        oneTestBook.setPages("123");
        oneTestBook.setPublisher("Norma");
        oneTestBook.setTitle("El color que cayo del cielo");
        oneTestBook.setSubtitle("Terror cosmico");
        oneTestBook.setYear("1915");

        oneTestUser.setUserName("Silver");
        oneTestUser.setName("Pedro Fleitas");
        oneTestUser.setBirthDate(LocalDate.parse("2020-08-23"));
        oneTestUser.addBook(oneTestBook);
    }

    @Test
    public void whenDeleteUserWhichExists_thenOkResponse() throws Exception {
        Mockito.when(mockUserRepository.findFirstByUserName("Silver")).thenReturn(Optional.ofNullable(oneTestUser));
        mvc.perform(get("/api/users/name/Silver")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindByIdWhichExists_thenUserIsReturned() throws Exception {
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.ofNullable(oneTestUser));
        mvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName", is(oneTestUser.getUserName())))
                .andExpect(jsonPath("name", is(oneTestUser.getName())))
                .andExpect(jsonPath("birthDate", is(oneTestUser.getBirthDate().toString())))
        ;

    }
}
