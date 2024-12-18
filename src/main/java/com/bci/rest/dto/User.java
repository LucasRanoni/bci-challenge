package com.bci.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Schema(description = "User")
public class User {

    @Schema(description = "name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Schema(description = "email")
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @Schema(description = "password")
    @NotEmpty(message = "Password cannot be null")
    private String password;

    private List<Phone> phones;

    public User(String name, String email, String password, List<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public User() {
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
