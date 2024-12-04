package Gwesty.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRoomTypePage {
    WebDriver driver;
    By titleTextboxLocator = By.id("txtRoomName");
    public AddRoomTypePage(WebDriver driver) {
        this.driver = driver;
    }

}
