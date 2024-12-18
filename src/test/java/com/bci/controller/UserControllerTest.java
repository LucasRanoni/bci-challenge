package com.bci.controller;

import com.bci.rest.dto.User;
import com.bci.rest.dto.UserResponse;
import com.bci.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebSecurity
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.clearContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "user", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void saveUser_isOK() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(userService.saveUser(any())).thenReturn(getUserResponse());
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(getUser()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(getUserResponse().getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(getUserResponse().getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is(getUserResponse().getPassword())));
    }

    @Test
    void saveUserWithoutJson_isUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/users")
                        .content(getUser().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());
    }

    @Test
    void deleteUser_isNoContent() throws Exception {
        UUID someUserId = UUID.randomUUID();

        doNothing().when(userService).deleteUser(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", someUserId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = getUser();
        UserResponse userResponse = getUserResponse();
        UUID userId = userResponse.getId();
        when(userService.updateUser(any(), any())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", userId)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userResponse.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(userResponse.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(userResponse.getEmail())));
    }

    private static UserResponse getUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(UUID.randomUUID());
        userResponse.setName("user");
        userResponse.setEmail("user@gmail.com");
        userResponse.setPassword("password");
        return userResponse;
    }
    private static User getUser() {
        User user = new User();
        user.setName("user");
        user.setPassword("password");
        user.setEmail("user@gmail.com");
        return user;
    }
}
