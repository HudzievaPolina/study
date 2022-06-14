package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CRUDRestController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CRUDRestController(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping ("/authUser")
    public User authUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public List<User> addNewUser(@RequestBody User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        userService.saveUser(user);
        return userService.getAllUsers();
    }

    @PutMapping("/users")
    public List<User> updateUser(@RequestBody User user) {
        User oldUser = userService.getUserById(user.getId());
        if(oldUser == null || !oldUser.getPass().equals(user.getPass())) {
            user.setPass(passwordEncoder.encode(user.getPass()));
        }
        userService.saveUser(user);
        return userService.getAllUsers();
    }
    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return userService.getAllUsers();
    }

}