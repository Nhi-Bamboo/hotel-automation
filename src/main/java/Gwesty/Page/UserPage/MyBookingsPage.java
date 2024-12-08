package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class MyBookingsPage {
    By dynamicCancelButtonLocator;
    By cancelBookingButtonLocator = By.xpath("//div[@id='cancel-booking'][@style='display: block;']//input[@value='Cancel']");
    By dynamicIdBookingLocator;
    By idBookingLocator = By.className("widget_ratting");
    By noBookingsMessageLocator = By.xpath("//h1[text()='You have no bookings here.']");

    public MyBookingsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void clickCancelButtonById(String id){
        String xpathValue = String.format("//strong[contains(text(),'#%s')]/ancestor::div//a[@class='btn btn-danger']", id);
        dynamicCancelButtonLocator = By.xpath(xpathValue);
        driver.findElement(dynamicCancelButtonLocator).click();
    }

    public void cancelBooking(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBookingButtonLocator));
        driver.findElement(cancelBookingButtonLocator).click();
    }

    public boolean isIdBookingDisplayed(String id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelBookingButtonLocator));
        String xpathValue = String.format("//*[contains(text(),'#%s')]", id);
        dynamicIdBookingLocator = By.xpath(xpathValue);
        return driver.findElement(dynamicIdBookingLocator).isDisplayed();
    }

    public int getBookingCount() {
        return driver.findElements(idBookingLocator).size();
    }

    public boolean isNoBookingMessageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelBookingButtonLocator));
        return driver.findElement(noBookingsMessageLocator).isDisplayed();
    }

    //Kiem tra so luong booking
    public void verifyCancelBooking(String id, SoftAssert softAssert) {
        int bookingCount = getBookingCount();
        System.out.println("Booking count: " + bookingCount);
        if (bookingCount == 0) {
            softAssert.assertTrue(isNoBookingMessageDisplayed(),"Message ko hiển thị");
            System.out.println(driver.findElement(noBookingsMessageLocator).getText());
        } else {
            softAssert.assertFalse(isIdBookingDisplayed(id),"ID vẫn hiển thị");
        }
    }
}
