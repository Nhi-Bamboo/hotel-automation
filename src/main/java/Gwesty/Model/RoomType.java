package Gwesty.Model;

import java.util.Objects;

public class RoomType {
    String roomTypeTitle;
    double price;
    int adult;
    int children;
    String description;

    public String getRoomTypeTitle() {
        return roomTypeTitle;
    }

    public void setRoomTypeTitle(String roomTypeTitle) {
        this.roomTypeTitle = roomTypeTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
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
        RoomType roomType = (RoomType) o;
        return Double.compare(price, roomType.price) == 0 && adult == roomType.adult && children == roomType.children && Objects.equals(roomTypeTitle, roomType.roomTypeTitle) && Objects.equals(description, roomType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeTitle, price, adult, children, description);
    }
}
