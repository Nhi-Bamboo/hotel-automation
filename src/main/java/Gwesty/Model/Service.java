package Gwesty.Model;

import java.util.Objects;

public class Service {
    //name + price
    private String name;
    private double price;
    private int quantity;
    private String unit;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(price, service.price) == 0 &&
                quantity == service.quantity &&
                Objects.equals(name, service.name) &&
                Objects.equals(unit, service.unit) &&
                Objects.equals(description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, unit, description);
    }
}