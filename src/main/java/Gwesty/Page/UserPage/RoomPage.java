package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RoomPage {
    By roomLocator = By.xpath("//div[@class='most_pop_item_blog clearfix']");
    By roomsLabelLocator = By.xpath("//h2[text()='Rooms']");
    By viewDetailRoomLocator = By.linkText("View Details");

    public RoomPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isRoomsDisplayed(){
        return driver.findElement(roomLocator).isDisplayed();
    }

    public void openDetailRoomByIndex(int n){
        List<WebElement> list = driver.findElements(viewDetailRoomLocator);
        list.get(n-1).click();
    }

    public boolean isRoomsLabelDisplayed(){
        return driver.findElement(roomsLabelLocator).isDisplayed();
    }


}
