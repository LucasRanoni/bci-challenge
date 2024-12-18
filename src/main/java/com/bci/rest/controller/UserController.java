package com.bci.rest.controller;

import com.bci.rest.dto.User;
import com.bci.rest.dto.UserResponse;
import com.bci.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Get Users")
    @GetMapping("users")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @Operation(description = "Save User")
    @PostMapping(value = "users", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> saveUser(@Parameter(description = "User", required = true) @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody User user, @Valid @PathVariable("id") UUID userId) {
        return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") UUID userId) {
        userService.deleteUser(userId);
    }

}
