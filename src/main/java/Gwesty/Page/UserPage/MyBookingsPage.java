package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        return driver.findElements(dynamicIdBookingLocator).size() > 0;
    }

    public int getBookingCount() {
        return driver.findElements(idBookingLocator).size();
    }

    public boolean isNoBookingMessageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelBookingButtonLocator));
        return driver.findElement(noBookingsMessageLocator).isDisplayed();
    }


}
