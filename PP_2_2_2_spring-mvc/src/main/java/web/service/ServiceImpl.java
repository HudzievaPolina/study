package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceImpl implements Service{
    private List<Car> carList = new ArrayList<>();

    public ServiceImpl() {
        this.carList.add(new Car("Ferrari", 1969, "red"));
        this.carList.add(new Car("Buick", 1959, "gold"));
        this.carList.add(new Car("Porshe", 1963, "silver"));
        this.carList.add(new Car("Jaguar", 1961, "black"));
        this.carList.add(new Car("AC Cobra", 1967, "blue"));
    }

    public List<Car> getCars(int amount) {
        if (amount >= 5) {
            return carList;
        } else {
            return carList.subList(0, amount);
        }
    }

    public List<Car> getCarList() {
        return carList;
    }
}
