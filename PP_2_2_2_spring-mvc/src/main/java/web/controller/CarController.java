package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.Service;
import web.service.ServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller

public class CarController {
    @Autowired
    Service service;

    @GetMapping(value = "/cars")
    public String carsList(HttpServletRequest request, Model model) {
        String amount = request.getParameter("count");
        if(amount != null) {
            model.addAttribute("carList", service.getCars(Integer.parseInt(amount)));
        } else {
            model.addAttribute("carList", service.getCarList());
        }
        return "Cars";
    }
}
