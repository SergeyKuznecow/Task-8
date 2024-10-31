package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserDTOServiceImpl;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImp;
import ru.itmentor.spring.boot_security.demo.util.UserNotFoundException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;

    private final UserDTOServiceImpl userDTOService;

    @Autowired
    public AdminController(UserServiceImp userService, UserDTOServiceImpl userDTOService) {
        this.userService = userService;
        this.userDTOService = userDTOService;
    }

    @GetMapping("/users")
    public List<User> showUserList() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User infoUser(@PathVariable("id") int id) {
        return userService.readUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        userService.createUser(userDTOService.convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<String> editUserInfo(@PathVariable("id") int id, @RequestBody @Valid UserDTO userDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        userService.editeUser(id, userDTOService.convertToUser(userDTO));
        return ResponseEntity.ok("Информация о пользователе успешно обновлена.");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь успешно удален.");
    }
}
