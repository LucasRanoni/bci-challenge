package com.bci.service;

import com.bci.model.UserEntity;
import com.bci.repository.UserRepository;
import com.bci.rest.dto.User;
import com.bci.rest.dto.UserResponse;
import com.bci.util.EmailValidator;
import com.bci.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @MockBean
    protected JwtUtil jwtUtil;

    @MockBean
    protected EmailValidator emailValidator;

    private static final String JWT_TOKEN = "JWT_TOKEN_SECURITY";

    @Test
    void saveUser() {
        User user = new User();
        user.setName("user");
        user.setPassword("password");
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(getUserEntity());
        when(jwtUtil.getJWTToken(any())).thenReturn(JWT_TOKEN);
        when(emailValidator.isValidEmail(any())).thenReturn(Boolean.TRUE);
        UserResponse userResponse = userService.saveUser(user);

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals(userResponse.getName(), user.getName());
        Assertions.assertEquals(userResponse.getPassword(), user.getPassword());
    }

    @Test
    void updateUser() {
        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setName("user");
        user.setPassword("password");
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(getUserEntity());
        when(jwtUtil.getJWTToken(any())).thenReturn(JWT_TOKEN);
        when(emailValidator.isValidEmail(any())).thenReturn(Boolean.TRUE);
        UserResponse userResponse = userService.updateUser(user,uuid);

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals(userResponse.getName(), user.getName());
        Assertions.assertEquals(userResponse.getPassword(), user.getPassword());
        Assertions.assertEquals(JWT_TOKEN, userResponse.getToken());
    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("user");
        userEntity.setEmail("user@gmail.com");
        return userEntity;
    }
}