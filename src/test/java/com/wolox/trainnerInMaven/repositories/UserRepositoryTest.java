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

import java.time.LocalDate;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

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
        oneTestUser.setUserName("ramselton");
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


}


