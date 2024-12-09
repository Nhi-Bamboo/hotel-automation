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
    public void clickMenu(String option) {
        String xpathMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathMenu)).click();
        //waiting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated((submenu)));
    }
    public void clickSubMenu(String option) {
        String xpathSubMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathSubMenu)).click();
    }

}
