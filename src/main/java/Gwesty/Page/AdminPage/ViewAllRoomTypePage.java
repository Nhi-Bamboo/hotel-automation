package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import Gwesty.Model.RoomType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewAllRoomTypePage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By addNewButtonLocator = By.id("addRow");
    By roomTypesTitleLocator = By.xpath("//td[1]");
    By adultCapacityLocator = By.xpath("//tr[@class='gradeX odd']/td[2]");
    By childrenCapacityLocator = By.xpath("//tr[@class='gradeX odd']/td[3]");
    By priceLocator = By.xpath("//tr[@class='gradeX odd']/td[4]");

    public ViewAllRoomTypePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchByTitle(String keyword) {
        driver.findElement(searchTextboxLocator).sendKeys(keyword);

    }

    public boolean isRoomTypeDisplayed(String title) {
        String xpathTitle = String.format("//tbody/tr[@role='row']/td[text()='%s']",title);
        return driver.findElement(By.xpath(xpathTitle)).isDisplayed();
    }

    public int countRoomTypesByTitle(String title) {
        String xpathTitle = String.format("//tbody/tr[@role='row']/td[text()='%s']", title);
        List<WebElement> items = driver.findElements(By.xpath(xpathTitle));
        return items.size();
    }
    public void clickAddNewButton() {
        driver.findElement(addNewButtonLocator).click();
    }

    public String getRoomTypesTitle(int i) {
        return driver.findElements(roomTypesTitleLocator).get(i-1).getText();
    }

    public String getRoomTypesTitleByIndex(int i) {
        List<WebElement> list = driver.findElements(roomTypesTitleLocator);
        return list.get(i-1).getText();
    }

    public int getAdultCapacity(int i) {
        return Integer.parseInt(driver.findElements(adultCapacityLocator).get(i-1).getText());
    }

    public int getChildrenCapacity(int i) {
        return Integer.parseInt(driver.findElements(childrenCapacityLocator).get(i-1).getText());
    }

    public float getPrice(int i) {
        return Float.parseFloat(driver.findElements(priceLocator).get(i-1).getText());
    }

    public RoomType getRoomTypeByIndex(int i) {
        RoomType r = new RoomType();
        r.setRoomTypeTitle(getRoomTypesTitle(i));
        r.setAdult(getAdultCapacity(i));
        r.setChildren(getChildrenCapacity(i));
        r.setPrice(getPrice(i));
        return r;
    }

}
