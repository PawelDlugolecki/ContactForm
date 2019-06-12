package com.app;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RepositoryTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test 1")
    public void test1() {

        //Given
        Mockito
                .when(userRepository.findAll())
                .thenReturn(Arrays.asList(
                        User.builder().name("Pawel").surname("Dlugolecki").build(),
                        User.builder().name("Adam").surname("Wisnia").build()
                ));
        //When
        List<User> users = userService.getAllUsers();

        //Then
        Assertions.assertAll(
                "Test 1 - users find all test",
                () -> Assertions.assertEquals(2, users.size(), "TEST 1.1 OK"),
                () -> Assertions.assertEquals("Pawel", users.get(0).getName(), "TEST 1.2 OK")
        );
    }
}
