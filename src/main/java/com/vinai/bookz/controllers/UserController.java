package com.vinai.bookz.controllers;

import com.vinai.bookz.dtos.UserDTO;
import com.vinai.bookz.exceptions.NotFoundException.UserNotFound;
import com.vinai.bookz.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public List<UserDTO.UserData> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO.UserDetail getUser(@PathVariable long id) throws UserNotFound {
        return userService.getUser(id);
    }

    @PostMapping("")
    public UserDTO.UserDetail createUser(@RequestBody @Valid UserDTO.UserCreate user) {
        return userService.createUser(user);
    }

    @PatchMapping("{id}")
    public UserDTO.UserDetail updateUser(@PathVariable long id, @RequestBody @Valid UserDTO.UserUpdate user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
