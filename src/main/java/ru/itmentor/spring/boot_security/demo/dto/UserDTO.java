package ru.itmentor.spring.boot_security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name shoud be between 2 and 30 characters")
    private String username;


    @Min(value = 16, message = "Age should be greater than 16")
    private int age;


    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    private String password;

    private List<Integer> roles;
}
