package com.coderhack.controllers;

import com.coderhack.entities.Badge;
import com.coderhack.exchanges.user.UserRequest;
import com.coderhack.exchanges.user.UserResponse;
import com.coderhack.services.user.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Mocks the IUserService to avoid requiring a real implementation
    private IUserService userService;

    @Test
    void registerUser() throws Exception {

        Mockito.when(this.userService.register(new UserRequest("1", "JohnDoe"))).thenReturn("1");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"userName\": \"JohnDoe\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(this.userService.getUserById("1"))
                .thenReturn(new UserResponse("1", "JohnDoe", 0, new LinkedHashSet<>()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.userName").value("JohnDoe"))
                .andExpect(jsonPath("$.score").value("0"));
    }

    @Test
    void getAllUsers() throws Exception {
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse("1", "JohnDoe", 0, new LinkedHashSet<>()));

        Mockito.when(this.userService.getAllUsers())
                .thenReturn(users);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserScore() throws Exception {
        LinkedHashSet<Badge> badges = new LinkedHashSet<>();
        badges.add(Badge.CODE_NINJA);

        Mockito.when(this.userService.updateScore("1", 1))
                .thenReturn(new UserResponse("1", "JohnDoe", 1, badges));

         mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
                        .param("score", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())  // Assert the status is 200 OK
                .andExpect(jsonPath("$.id").value("1"))  // Assert the id is "1"
                .andExpect(jsonPath("$.userName").value("JohnDoe"))  // Assert the userName is "JohnDoe"
                .andExpect(jsonPath("$.score").value(1))  // Assert the score is 1 (as integer)
                .andExpect(jsonPath("$.badges[0]").value("CODE_NINJA"));
    }

    @Test
    void deleteUser() throws Exception {
        Mockito.when(this.userService.deleteUser("1"))
                .thenReturn("Successfully deleted user !!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully deleted user !!"));
    }
}