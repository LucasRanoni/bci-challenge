package com.bci.service;

import com.bci.model.UserEntity;
import com.bci.rest.dto.User;
import com.bci.rest.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponse> getUsers();

    UserResponse saveUser(User user);

    UserResponse updateUser(User user, UUID userId);

    void deleteUser(UUID userId);
}
