package Gwesty.Page.AdminPage;

import Gwesty.Model.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewAllRoomsPage {
    public ViewAllRoomsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isRoomDisplayed(int roomNumber, String roomType, int floor, int status){
        String xpath = String.format("//tr[1][td[1][text()='%d'] " +
                                          "and td[2][text()='%s'] " +
                                          "and td[3][text()='%d'] " +
                                          "and td[4][text()='%d']]",
                                    roomNumber, roomType, floor, status);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public int getRoomNoByIndex(int i){
        String xpathNo = String.format("//tr[%d]/td[1]",i);
        return Integer.parseInt(driver.findElement(By.xpath(xpathNo)).getText());
    }

    public String getRoomTypeByIndex(int i){
        String xpathType = String.format("//tr[%d]/td[2]",i);
        return driver.findElement(By.xpath(xpathType)).getText();
    }

    public int getFloorByIndex(int i){
        String xpathFloor = String.format("//tr[%d]/td[3]",i);
        return Integer.parseInt(driver.findElement(By.xpath(xpathFloor)).getText());
    }

    public boolean getStatusByIndex(int i){
        String xpathStatus = String.format("//tr[%d]/td[4]",i);
        return Integer.parseInt(driver.findElement(By.xpath(xpathStatus)).getText()) == 1;
    }

    public Room getRoomByIndex(int i){
        Room room = new Room();
        room.setRoomNo(getRoomNoByIndex(i));
        room.setType(getRoomTypeByIndex(i));
        room.setFloor(getFloorByIndex(i));
        room.setStatus(getStatusByIndex(i));
        return room;
    }

}
