package com.fitness.backend.controllers;

import com.fitness.backend.entities.User;
import com.fitness.backend.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new User();
        user.setName("Silva Stone");
        user.setUsername("silvaStone23");
        user.setEmail("silvverstone@example.com");
        user.setGender("Male");
        user.setDateOfBirth("2003-09-10");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(userService.findAll()).thenReturn(userList);
        Mockito.when(userService.findById(1L)).thenReturn(user);
        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.update(Mockito.anyLong(), Mockito.any(User.class))).thenReturn(user);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers_validRequest_allUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Silva Stone"));
    }

    @Test
    void getUserById_validId_correctUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("silvverstone@example.com"));
    }


    @Test
    void deleteUser_validRequest_userDeleted() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).delete(1L);
    }
}
