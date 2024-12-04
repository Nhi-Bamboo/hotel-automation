package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchRoomsPage {
    By messageLocator = By.className("mmb-blc-title");

    public SearchRoomsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isMessageDisplayed(){
        return driver.findElement(messageLocator).isDisplayed();
    }
}
