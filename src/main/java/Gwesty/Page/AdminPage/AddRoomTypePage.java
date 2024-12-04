package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRoomTypePage {
    WebDriver driver;
    By titleTextboxLocator = By.id("txtRoomName");
    By priceTextboxLocator = By.xpath("//input[@name='price']");
    By adultCapacityTextboxLocator = By.xpath("//input[@name='adult']");
    By childrenCapacityTextboxLocator = By.xpath("//input[@name='children']");
    By descriptionTextboxLocator = By.xpath("//textarea[@name='description']");
    By submitButtonLocator = By.xpath("//button[text()='Submit']");
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    public AddRoomTypePage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterTitle(String title) {
        driver.findElement(titleTextboxLocator).sendKeys(title);
    }
    public void enterPrice(String price) {
        driver.findElement(priceTextboxLocator).sendKeys(price);
    }
    public void enterAdultCapacity(String number) {
        String xpathSelectNumber = String.format("//ul[@data-mdl-for='list2']/li[@data-val='%s']",number);
        driver.findElement(adultCapacityTextboxLocator).click();
        driver.findElement(By.xpath(xpathSelectNumber)).click();
    }
    public void enterChildrenCapacity(String number) {
        String xpathSelectNumber = String.format("//ul[@data-mdl-for='list3']/li[@data-val='%s']",number);
        driver.findElement(childrenCapacityTextboxLocator).click();
        driver.findElement(By.xpath(xpathSelectNumber)).click();
    }
    public void enterDescription(String description) {
        driver.findElement(descriptionTextboxLocator).sendKeys(description);
    }
    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextboxLocator));
    }
}
