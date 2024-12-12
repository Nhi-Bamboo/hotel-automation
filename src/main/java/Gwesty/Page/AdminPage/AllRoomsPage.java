package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllRoomsPage {
    By searchTextBoxLocator = By.xpath("//input[@type='search']");

    public AllRoomsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void searchByRoomNumber(int roomNo) {
        driver.findElement(searchTextBoxLocator).sendKeys(String.valueOf(roomNo));

    }

    public int quantityOfRoom(int roomNo) {
        String xpath = String.format("//td[@class='center sorting_1'][text()='%d']", roomNo);
        List<WebElement> items = driver.findElements(By.xpath(xpath));
        return items.size();
    }


}
