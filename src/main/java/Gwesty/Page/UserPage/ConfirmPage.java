package Gwesty.Page.UserPage;

import Gwesty.Model.Booking;
import Gwesty.Model.CreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPage {
    By messageSuccessfulLocator = By.xpath("//div[@class='alert alert-success']");
    By pageNameLocator = By.xpath("//h2[text()='Confirm']");
    By idBookingLocator = By.xpath("//span[contains(text(),'Id: ')]");
    By roomTypeTitleLocator = By.xpath("//div[@class='row']/div/h5");
    By numberOfRoomLocator = By.xpath("//div[@class='row']/div/p/span[@class='float-left']");
    By checkInLocator = By.xpath("//div[@class='conform_date clear']/ul/li[1]/p");
    By checkOutLocator = By.xpath("//div[@class='conform_date clear']/ul/li[3]/p");
    By numberOfAdultLocator = By.xpath("//p[@class='getsts_cont']/span[1]");
    By numberOfChildrenLocator = By.xpath("//p[@class='getsts_cont']/span[2]");

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

    public String getRoomTypeTitle() {
        return driver.findElement(roomTypeTitleLocator).getText();
    }

    public String getNumberOfRoom() {
        return driver.findElement(numberOfRoomLocator).getText();
    }

    public String getCheckIn() {
        return driver.findElement(checkInLocator).getText();
    }

    public String getCheckOut() {
        return driver.findElement(checkOutLocator).getText();
    }

    public String getAdult() {
        return driver.findElement(numberOfAdultLocator).getText();
    }

    public String getChildren() {
        return driver.findElement(numberOfChildrenLocator).getText();
    }

    public Booking getConfirmBookingInformation() {
        Booking b = new Booking();
        b.setIdBooking(getIDBooking());
        b.setTitle(getRoomTypeTitle());
        b.setNumberOfRoom(getNumberOfRoom());
        b.setCheckIn(getCheckIn());
        b.setChidren(getCheckOut());
        b.setAdult(getAdult());
        b.setChidren(getChildren());
        return b;
    }
}
