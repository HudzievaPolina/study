package spring_mvc_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_mvc_hibernate.dao.UserDAO;
import spring_mvc_hibernate.entity.User;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userDAO.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }
}
