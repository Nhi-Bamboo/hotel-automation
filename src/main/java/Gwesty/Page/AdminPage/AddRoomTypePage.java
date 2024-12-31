package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import Gwesty.Model.RoomType;
import io.qameta.allure.Allure;
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
    private void enterTitle(String title) {
        driver.findElement(titleTextboxLocator).sendKeys(title);
    }
    private void enterPrice(double price) {
        String p = String.valueOf(price);
        driver.findElement(priceTextboxLocator).sendKeys(p);
    }
    private void enterAdultCapacity(int number) {
        String n = String.valueOf(number);
        String xpathSelectNumber = String.format("//ul[@data-mdl-for='list2']/li[@data-val='%s']",n);
        driver.findElement(adultCapacityTextboxLocator).click();
        driver.findElement(By.xpath(xpathSelectNumber)).click();
    }
    private void enterChildrenCapacity(int number) {
        String n = String.valueOf(number);
        String xpathSelectNumber = String.format("//ul[@data-mdl-for='list3']/li[@data-val='%s']",n);
        driver.findElement(childrenCapacityTextboxLocator).click();
        driver.findElement(By.xpath(xpathSelectNumber)).click();
    }
    private void enterDescription(String description) {
        driver.findElement(descriptionTextboxLocator).sendKeys(description);
    }
    public void clickSubmitButton() {
        Allure.step("Click Submit Button");
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextboxLocator));
    }

    public void addRoomTypeInformation(RoomType roomType) {
        Allure.step("Enter room type information");
        enterTitle(roomType.getRoomTypeTitle());
        enterPrice(roomType.getPrice());
        enterAdultCapacity(roomType.getAdult());
        enterChildrenCapacity(roomType.getChildren());
        enterDescription(roomType.getDescription());
    }
}
