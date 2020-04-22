package com.wolox.trainnerInMaven.repositories;

import com.wolox.trainnerInMaven.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User oneTestUser;

    @Before
    public void setUp() {
        oneTestUser = new User();
        oneTestUser.setUserName("Ramiro");
        oneTestUser.setPassword("1234");
        oneTestUser.setName("Ramiro Selton");
        oneTestUser.setBirthDate(LocalDate.parse("1999-03-27"));
        entityManager.persist(oneTestUser);
        entityManager.flush();
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        User userExist = userRepository.findFirstByUserName(oneTestUser.getUserName()).get();
        Assertions.assertEquals(userExist.getUserName(), oneTestUser.getUserName());
    }

    @Test
    public void whenSetUserWithoutUsername_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> oneTestUser.setUserName(null));
    }

    @Test
    public void whenCreateUserWithEmptyName_thenThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> oneTestUser.setUserName(""));
    }

    @Test
    public void whenCreateUser_thenUserIsPersisted() {
        User persistedUser = userRepository.findFirstByUserName(oneTestUser.getUserName())
                .orElse(new User());
        Assertions.assertEquals(persistedUser.getUserName(), oneTestUser.getUserName());
    }
}
