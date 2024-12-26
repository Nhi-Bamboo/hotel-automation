package Gwesty.Model;

import java.util.Objects;

public class Booking {
    String idBooking;
    String title;
    String numberOfRoom;
    String checkIn;
    String checkOut;
    String adult;
    String chidren;

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChidren() {
        return chidren;
    }

    public void setChidren(String chidren) {
        this.chidren = chidren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(idBooking, booking.idBooking) &&
                Objects.equals(title, booking.title)&&
                Objects.equals(numberOfRoom, booking.numberOfRoom) &&
                Objects.equals(checkIn, booking.checkIn) &&
                Objects.equals(checkOut, booking.checkOut) &&
                Objects.equals(adult, booking.adult) &&
                Objects.equals(chidren, booking.chidren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBooking, title, numberOfRoom, checkIn, checkOut, adult, chidren);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "idBooking='" + idBooking + '\'' +
                ", title='" + title + '\'' +
                ", numberOfRoom='" + numberOfRoom + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", adult='" + adult + '\'' +
                ", chidren='" + chidren + '\'' +
                '}';
    }
}
