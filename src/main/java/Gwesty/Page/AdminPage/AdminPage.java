package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
    WebDriver driver;
    By roomTypesNavLinkLocator = By.xpath("//span[@class='title'][text()='Room Types']");
    By addRoomTypesNavLinkLocator = By.xpath("//span[@class='title'][text()='Add Room Type']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenAddRoomTypesPage() {
        driver.findElement(roomTypesNavLinkLocator).click();
        driver.findElement(addRoomTypesNavLinkLocator).click();
    }
}
