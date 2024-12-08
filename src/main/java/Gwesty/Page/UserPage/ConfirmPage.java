package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPage {
    By messageSuccessfulLocator = By.xpath("//div[@class='alert alert-success']");
    By pageNameLocator = By.xpath("//h2[text()='Confirm']");
    By idBookingLocator = By.xpath("//span[contains(text(),'Id: ')]");

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isMessageDisplayed(){
        return driver.findElement(messageSuccessfulLocator).isDisplayed();
    }

    public boolean isPageNameDisplayed(){
        return driver.findElement(pageNameLocator).isDisplayed();
    }

    public String getIDBooking(){
        return driver.findElement(idBookingLocator).getText().replace("Id: ","");
    }
}
