package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.CarService;

@Controller

public class CarController {

    private CarService service;
    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping(value = "/cars")
    public String carsList(@RequestParam(value = "count", defaultValue = "5") int amount, Model model) {

        model.addAttribute("carList", service.getCars(amount));

        return "Cars";
    }
}
