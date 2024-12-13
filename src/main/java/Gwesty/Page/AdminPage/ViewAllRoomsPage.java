package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewAllRoomsPage {
    public ViewAllRoomsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isRoomDisplayed(int roomNumber, String roomType, int floor, int status){
        String xpath = String.format("//tr[td[1][text()='%d'] " +
                                          "and td[2][text()='%s'] " +
                                          "and td[3][text()='%d'] " +
                                          "and td[4][text()='%d']]",
                                    roomNumber, roomType, floor, status);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }



}
