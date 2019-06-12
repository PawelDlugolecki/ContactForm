package com.app;

import com.app.controller.UserController;
import com.app.model.User;
import com.app.service.UserService;
import com.app.validators.UserValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class WebTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserValidation userValidation;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() {
        User u1 = User.builder().name("Pawel").surname("Dlugolecki").build();
        User u1Id = User.builder().id(1L).name("Pawel").surname("Dlugolecki").build();

        User u2 = User.builder().name("Adam").surname("Wisnia").build();
        User u2Id = User.builder().id(2L).name("Adam").surname("Wisnia").build();

        List<User> users = Arrays.asList(u1, u2);

        Mockito
                .when(userService.getAllUsers())
                .thenReturn(users);

        Mockito
                .when(userService.getOneUser(1L))
                .thenReturn(u1Id);

        Mockito
                .when(userService.addUser(u1))
                .thenReturn(u1Id);
    }
}
