package Gwesty.Model;

import java.util.Objects;

public class Room {
    //4 thộc tính
    private int roomNo;
    private String type;
    private int floor;
    private boolean status;
    private String desc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return  Integer.compare(roomNo, room.roomNo) == 0 &&
                Objects.equals(type, room.type) &&
                Integer.compare(floor, room.floor) == 0 &&
                status == room.status &&
                Objects.equals(desc, room.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, type, floor, status, desc);
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", type='" + type + '\'' +
                ", floor=" + floor +
                ", status=" + status +
                ", desc='" + desc + '\'' +
                '}';
    }
}