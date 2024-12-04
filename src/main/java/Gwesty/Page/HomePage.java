package Gwesty.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    By loginButtonLocator = By.linkText("Login");
    By checkInBoxLocator = By.id("check-in");
    By checkOutBoxLocator = By.id("check-out");
    By adultBoxLocator = By.xpath("//input[@name='adult']");
    By childrenBoxLocator = By.xpath("//input[@name='children']");
    By searchButtonLocator = By.xpath("//input[@class='btn btn-success btn-block']");
    By roomsMenuLocator = By.linkText("Rooms");
    By userNameTextBoxLocator = By.name("email");
    By accountAndSettingLocator = By.id("NavebarProfileDrop");
    By goToAdminButtonLocator = By.xpath("//a[text()='Go to Admin']");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void openLoginPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTextBoxLocator));
    }

    public void enterCheckInDay(String dateCI){
        //format yyyy/mm/dd
        driver.findElement(checkInBoxLocator).sendKeys(dateCI);
    }

    public void enterCheckOutDay(String dateCO){
        //format yyyy/mm/dd
        driver.findElement(checkOutBoxLocator).sendKeys(dateCO);
    }

    public void enterAdult(int adult){
        driver.findElement(adultBoxLocator).sendKeys(String.valueOf(adult));
    }

    public void enterChildren(int children){
        driver.findElement(childrenBoxLocator).sendKeys(String.valueOf(children));
    }

    public void clickSearchButton(){
        driver.findElement(searchButtonLocator).click();
    }

    public void searchRooms(String checkIn, String checkOut, int ad, int child){
        enterCheckInDay(checkIn);
        enterCheckOutDay(checkOut);
        enterAdult(ad);
        enterChildren(child);
        clickSearchButton();
    }

    public void selectRoomPage(){
        driver.findElement(roomsMenuLocator).click();
    }
    public void openPageAdmin() {
        driver.findElement(accountAndSettingLocator).click();
        driver.findElement(goToAdminButtonLocator).click();
    }


}
