package Gwesty.Page.UserPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    By loginButtonLocator = By.xpath("//a[@data-toggle='modal'][text()='Login']");
    By roomsMenuLocator = By.linkText("Rooms");
    By userNameTextBoxLocator = By.name("email");
    By goToAdminButtonLocator = By.linkText("Go to Admin");
    By accountSettingLocator = By.id("NavebarProfileDrop");
    By myBookingsLocator = By.linkText("My Bookings");
    By myAccountLocator = By.linkText("My Account");
    By logOutButtonLocator = By.xpath("//a[text()='Log Out']");
    By homeNavBarLocator = By.xpath("//ul[@class='navbar-nav']/li/a[text()='Home']");
    By searchBookingIdButtonLocator = By.xpath("//form[@id='searchForm']/span");
    By searchBookingIdTextboxLocator = By.id("search");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Step("Open Login Page")
    public void openLoginPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTextBoxLocator));
    }

    @Step("Open Room Page")
    public void selectRoomPage(){
        driver.findElement(roomsMenuLocator).click();
    }
    @Step("Open Admin Page")
    public void openPageAdmin() {
        Allure.step("Open dropdown menu: Account & Settings ");
        clickAccountSetting();
        Allure.step("Click on Go to Admin");
        driver.findElement(goToAdminButtonLocator).click();
    }

    public void clickAccountSetting(){
        driver.findElement(accountSettingLocator).click();
    }

    public void openMyBookingsPage(){
        Allure.step("Open dropdown menu: Account & Settings ");
        clickAccountSetting();
        Allure.step("Open My Bookings Page");
        driver.findElement(myBookingsLocator).click();
    }

    public void openMyAccountPage(){
        Allure.step("Open dropdown menu: Account & Settings ");
        clickAccountSetting();
        Allure.step("Open My Account Page");
        driver.findElement(myAccountLocator).click();
    }

    public void Logout() {
        Allure.step("Logout");
        clickAccountSetting();
        driver.findElement(logOutButtonLocator).click();
    }

    @Step("Open Home Page")
    public void openHomePage() {
        driver.findElement(homeNavBarLocator).click();
    }

    public void searchByBookingId(String booking) {
        Allure.step("Search By Booking ID");
        driver.findElement(searchBookingIdButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBookingIdTextboxLocator));
        driver.findElement(searchBookingIdTextboxLocator).sendKeys(booking+ Keys.ENTER);
    }

}
