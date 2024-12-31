package Gwesty.Model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class GuestInRoom {
    int room;
    int id;
    String fullName;
    String gender;
    String dateOfBirth;
    String address;
    String type;

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestInRoom that = (GuestInRoom) o;
        return room == that.room && id == that.id && Objects.equals(fullName, that.fullName) && Objects.equals(gender, that.gender) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(address, that.address) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, id, fullName, gender, dateOfBirth, address, type);
    }

    @Override
    public String toString() {
        return "GuestInRoom{" +
                "room=" + room +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
