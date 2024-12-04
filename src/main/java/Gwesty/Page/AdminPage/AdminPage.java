package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
    WebDriver driver;

    By viewAllRoomTypeNavLinkLocator = By.xpath("//span[@class='title'][text()='View All Room Types']");
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickMenu(String option) {
        String xpathMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathMenu)).click();
    }
    public void clickSubMenu(String option) {
        String xpathSubMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathSubMenu)).click();
    }

}
