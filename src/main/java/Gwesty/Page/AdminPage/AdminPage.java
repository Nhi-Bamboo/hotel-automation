package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    WebDriver driver;
    By submenu = By.xpath("//li[@class='nav-item open']");
    By viewAllRoomTypeNavLinkLocator = By.xpath("//span[@class='title'][text()='View All Room Types']");
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }
    private void clickMenu(String option) {
        String xpathMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathMenu)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated((submenu)));
    }
    private void clickSubMenu(String option) {
        String xpathSubMenu = String.format("//span[@class='title'][normalize-space(text())='%s']",option);
        driver.findElement(By.xpath(xpathSubMenu)).click();
    }

    public void openAllRoomTypePage(){
        clickMenu("Room Types");
        clickSubMenu("View All Room Types");
    }

    public void openAddRoomTypePage(){
        clickMenu("Room Types");
        clickSubMenu("Add Room Type");
    }

    public void openAllRoomPage(){
        clickMenu("Rooms");
        clickSubMenu("View All Rooms");
    }

    public void openAddRoomPage(){
        clickMenu("Rooms");
        clickSubMenu("Add Room");
    }

}
