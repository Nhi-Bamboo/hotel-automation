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
    }
    private void clickSubMenu(String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String xpathSubMenu = String.format("//span[@class='title'][normalize-space(text())='%s']",option);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSubMenu)));
        driver.findElement(By.xpath(xpathSubMenu)).click();
    }

    public void openAllRoomTypePage(){
        clickMenu("Room Types");
        clickSubMenu("View All Room Types");
    }

    public void openBookingPage() {
        clickMenu("Booking");
    }

    public void openAddRoomPage(){
        clickMenu("Rooms");
        clickSubMenu("Add Room");
    }
    public void openViewAllCreditCard() {
        clickMenu("CreditCard");
        clickSubMenu("View All CreditCard");
    }
}
