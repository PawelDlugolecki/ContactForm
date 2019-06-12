package com.app;

import com.app.model.User;
import com.app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1() {
        User u1 = User.builder()
                .name("Pawel")
                .surname("Dlugolecki")
                .address("Brok")
                .phone("880967717")
                .build();

        User u2 = User.builder()
                .name("Adam")
                .surname("Wisnia")
                .address("Warszawa")
                .phone("888999777")
                .build();

        testEntityManager.persist(u1);
        testEntityManager.persist(u2);
        testEntityManager.flush();

        List<User> users = userRepository.findAll();
        Assertions.assertEquals(2, users.size(), "T1");
        Assertions.assertEquals("Pawel", users.get(0).getName(), "T1");
    }
}
