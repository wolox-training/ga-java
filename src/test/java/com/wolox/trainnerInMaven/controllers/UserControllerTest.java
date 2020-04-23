package com.wolox.trainnerInMaven.controllers;

import com.wolox.trainnerInMaven.config.BCryptEncoder;
import com.wolox.trainnerInMaven.models.Book;
import com.wolox.trainnerInMaven.models.User;
import com.wolox.trainnerInMaven.repositories.BookRepository;
import com.wolox.trainnerInMaven.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserRepository mockUserRepository;

    @MockBean
    private BookRepository mockBookRepository;

    @MockBean
    private User oneTestUser;

    @MockBean
    private Book oneTestBook;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

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
        oneTestUser.setPassword("1234");
        oneTestUser.setName("Pedro Fleitas");
        oneTestUser.setBirthDate(LocalDate.parse("2020-08-23"));
        oneTestUser.addBook(oneTestBook);
    }

    @Test
    public void whenGetUserWhichExists_thenOkResponse() throws Exception {
        Mockito.when(mockUserRepository.findFirstByUserName("Silver")).thenReturn(Optional.ofNullable(oneTestUser));
        mvc.perform(get("/api/users/name/Silver")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindByIdWhichExists_thenUserIsReturned() throws Exception {
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.ofNullable(oneTestUser));
        Mockito.when(mockBookRepository.findById(1L)).thenReturn(Optional.ofNullable(oneTestBook));
        mvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userName", is(oneTestUser.getUserName())))
                .andExpect(jsonPath("name", is(oneTestUser.getName())))
                .andExpect(jsonPath("birthDate", is(oneTestUser.getBirthDate().toString())))
        ;
    }

    @Test
    public void whenGetUserByNameEmpty_thenReturnBadRequest() throws Exception{
        mvc.perform(get("/api/users/name/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetUserWhichExists_thenUserNotFound() throws Exception {
        Mockito.when(mockUserRepository.findById(4L)).thenReturn(Optional.empty());
        mvc.perform(get("/api/users/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
