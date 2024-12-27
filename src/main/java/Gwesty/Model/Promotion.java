package Gwesty.Model;

import java.util.Objects;

public class Promotion {
    private String name;
    private String code;
    private String startDate;
    private String endDate;
    private String type;
    private int value;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return value == promotion.value && Objects.equals(name, promotion.name) && Objects.equals(code, promotion.code) && Objects.equals(startDate, promotion.startDate) && Objects.equals(endDate, promotion.endDate) && Objects.equals(type, promotion.type) && Objects.equals(description, promotion.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, startDate, endDate, type, value, description);
    }
}
