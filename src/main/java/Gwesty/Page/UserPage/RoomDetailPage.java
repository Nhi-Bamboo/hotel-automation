package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RoomDetailPage {
    By checkInDayLocator = By.id("check-in");
    By checkOutDayLocator = By.id("check-out");
    By adultLocator = By.id("adult");
    By childrenLocator = By.id("children");
    By bookNowButtonLocator = By.xpath("//input[@value='Book Now']");
    By roomsDetailLabelLocator = By.xpath("//h2[text()='Room Details']");
    public RoomDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterCheckInDay(String dateCI){
        //format yyyy/mm/dd
        driver.findElement(checkInDayLocator).sendKeys(dateCI);
    }

    public void enterCheckOutDay(String dateCO){
        //format yyyy/mm/dd
        driver.findElement(checkOutDayLocator).sendKeys(dateCO);
    }

    public void enterAdult(int adult){
        driver.findElement(adultLocator).clear();
        driver.findElement(adultLocator).sendKeys(String.valueOf(adult));
    }

    public void enterChildren(int children){
        driver.findElement(childrenLocator).clear();
        driver.findElement(childrenLocator).sendKeys(String.valueOf(children));
    }
    public void enterBookingInformation(String checkin, String checkout, int adult, int children) {
        enterCheckInDay(checkin);
        enterCheckOutDay(checkout);
        enterAdult(adult);
        enterChildren(children);
    }

    public void clickBookNowButton(){
        driver.findElement(bookNowButtonLocator).click();
    }

    public void bookingRoom(String ci, String co, int a, int c){
        enterCheckInDay(ci);
        enterCheckOutDay(co);
        enterAdult(a);
        enterChildren(c);
        clickBookNowButton();
    }

    public boolean isRoomsDetailLabelDisplayed(){
        return driver.findElement(roomsDetailLabelLocator).isDisplayed();
    }


}
