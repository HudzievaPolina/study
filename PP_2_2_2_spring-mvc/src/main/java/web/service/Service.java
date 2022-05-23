package web.service;

import web.model.Car;

import java.util.List;

public interface Service {
    public List<Car> getCars(int amount);
    public List<Car> getCarList();
}
