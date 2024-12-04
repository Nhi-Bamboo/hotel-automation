package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewAllRoomTypePage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");

    public ViewAllRoomTypePage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterSearch(String keyword) {
        driver.findElement(searchTextboxLocator).sendKeys(keyword);
    }
    public boolean isRoomTypeDisplayed(String title) {
        String xpathTitle = String.format("//tr[@class='gradeX odd']/td[text()='%s']",title);
        return driver.findElement(By.xpath(xpathTitle)).isDisplayed();
    }
    public int quantityOfRoomType(String title) {
        String xpathTitle = String.format("//tr[@class='gradeX odd']/td[text()='%s']",title);
        List<WebElement> items = driver.findElements(By.id(xpathTitle));
        return items.size();
    }
}
