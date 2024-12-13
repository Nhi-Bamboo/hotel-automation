package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchOnPage {
    By searchTextBoxLocator = By.xpath("//input[@type='search']");

    public SearchOnPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void searchByRoomNumber(int roomNo) {
        driver.findElement(searchTextBoxLocator).sendKeys(String.valueOf(roomNo));

    }



}
