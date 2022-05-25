package web.model;

import java.util.Objects;

public class Car {
    private String model;
    private int year;
    private String color;

    public Car(String model, int year, String color) {
        this.model = model;
        this.year = year;
        this.color = color;
    }
    public Car () {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Car car = (Car) obj;
            return (car.getColor() == this.getColor() || (this.getColor() != null && this.getColor().equals(car.getColor())))
                    && (car.getModel() == this.getModel() || (this.getModel() != null && this.getModel().equals(car.getModel())))
                    && (car.getYear() == this.getYear());
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (color == null ? 0 : color.hashCode())
                + year + (model == null ? 0 : model.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "model: " + model + " year: " + year + " color: " + color;
    }
}
