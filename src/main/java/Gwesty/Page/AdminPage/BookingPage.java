package Gwesty.Page.AdminPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By iconViewDetailLocator = By.xpath("//td/a/i[@class='fa fa-eye']");
    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchByID(String id) {
        Allure.step("Search By Booking ID: " +id);
        driver.findElement(searchTextboxLocator).sendKeys(id);
    }


    public void openBookingDetail() {
        Allure.step("Open Booking Detail");
        driver.findElement(iconViewDetailLocator).click();
    }
}
