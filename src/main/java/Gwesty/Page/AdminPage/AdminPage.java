package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    WebDriver driver;
    By submenu = By.xpath("//li[@class='nav-item open']");
    By viewAllServiceLocator = By.xpath("//span[@class='title'][normalize-space(text())='View All Services']");
    By viewAllCreditCardLocator = By.xpath("//span[@class='title'][normalize-space(text())='View All CreditCard']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }
    private void clickMenu(String option) {
        String xpathMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathMenu)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private void clickSubMenu(String option) {

        String xpathSubMenu = String.format("//span[@class='title'][normalize-space(text())='%s']",option);
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllCreditCardLocator));

        clickSubMenu("View All CreditCard");
    }

    public void openViewAllServices() {
        clickMenu("Service");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllServiceLocator));
        clickSubMenu("View All Services");
    }

    public void openAddPromotionPage() {
        clickMenu("Promotion");
        clickSubMenu("Add Promotion");
    }
}
