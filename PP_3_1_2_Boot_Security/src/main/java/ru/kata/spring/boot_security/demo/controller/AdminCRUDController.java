package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminCRUDController {

    private UserService userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminCRUDController(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String showAllUsers(Authentication authentication, Model model) {
        List<User> allUsers = userService.getAllUsers();
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("authUser", user);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "all-users";
    }

@RequestMapping("/user")
public String user(Authentication authentication, Model model) {
    User user = userService.getUserByEmail(authentication.getName());
    model.addAttribute("authUser", user);
    return "admin-user";
}

    @RequestMapping({"/save", "/{id}"})
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "roles[]") Long[] roleIds) {
        Set<Role> roles = new HashSet<>();
        roles.addAll(roleRepository.findAllById(Arrays.asList(roleIds)));
        user.setRoles(roles);
        User oldUser = userService.getUserById(user.getId());
        if(oldUser == null || !oldUser.getPass().equals(user.getPass())) {
            user.setPass(passwordEncoder.encode(user.getPass()));
        }
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
