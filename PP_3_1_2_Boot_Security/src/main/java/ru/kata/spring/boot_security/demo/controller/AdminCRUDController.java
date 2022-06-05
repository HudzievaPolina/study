package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminCRUDController {

    private UserService userService;
    @Autowired
    public AdminCRUDController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @RequestMapping("/add")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(name = "roles") String rolesLine) {
        user.setPass("1");
        Set<Role> roles = new HashSet<>();
        String[] roleNames = rolesLine.replace(" ", "").split(",");
        for (String roleName : roleNames) {
            roles.add(new Role(roleName));
       }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        StringBuilder sb = new StringBuilder();
        String roles = null;
        if (user.getRoles().size() >= 1) {
            user.getRoles().forEach(role -> sb.append(role.toString() + ", "));
            sb.substring(0, sb.length() - 2);
        roles = sb.toString();
        }
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roles);
        return "user-info";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
