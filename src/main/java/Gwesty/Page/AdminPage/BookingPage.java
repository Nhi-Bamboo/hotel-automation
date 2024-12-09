package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By iconViewDetailLocator = By.xpath("//td/a/i[@class='fa fa-eye']");
    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

//    public void openBookingDetailOnlinePendingLocator(String id) {
//        String xpathViewBookingDetailByID = String.format("//td[text()='%s']/following-sibling::td/a/i",id);
//        driver.findElement(By.xpath(xpathViewBookingDetailByID)).click();
//    }
    public void searchByID(String id) {
        driver.findElement(searchTextboxLocator).sendKeys(id);
    }
    public void openBookingDetail() {
        driver.findElement(iconViewDetailLocator).click();
    }
}
