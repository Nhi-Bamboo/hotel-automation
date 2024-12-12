package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRoomPage {
    By roomNumberTextBoxLocator = By.name("roomnumber");
    By roomTypeTextBoxLocator = By.name("roomType");
    By floorTextBoxLocator = By.name("floor");
    By statusCheckBoxLocator = By.xpath("//span[@class='slider aqua round']");
    By descriptionTextBoxLocator = By.id("education");
    By submitButtonLocator = By.xpath("//button[@type='submit']");

    public AddRoomPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterRoomNumber(int rn){
        driver.findElement(roomNumberTextBoxLocator).clear();
        driver.findElement(roomNumberTextBoxLocator).sendKeys(String.valueOf(rn));
    }

    public void enterRoomType(String rt){
        String xpath = String.format("//li[@class='mdl-menu__item'][text()='%s']",rt);
        driver.findElement(roomTypeTextBoxLocator).click();
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enterFloor (int floor){
        driver.findElement(floorTextBoxLocator).clear();
        driver.findElement(floorTextBoxLocator).sendKeys(String.valueOf(floor));
    }

    public void clickStatusCheckbox(){
        driver.findElement(statusCheckBoxLocator).click();
    }

    public void enterDescription(String desc){
        driver.findElement(descriptionTextBoxLocator).sendKeys(desc);
    }

    public void clickSubmitButton(){
        driver.findElement(submitButtonLocator).click();
    }

    public void addRoomInformation(int roomNo, String type, int floor, String desc){
        enterRoomNumber(roomNo);
        enterRoomType(type);
        enterFloor(floor);
        clickStatusCheckbox();
        enterDescription(desc);
        clickSubmitButton();
    }

}
