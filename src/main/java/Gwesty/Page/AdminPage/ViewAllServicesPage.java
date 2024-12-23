package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewAllServicesPage {
    WebDriver driver;
    By addRowButtonLocator = By.id("addRow");
    By searchTextboxLocator = By.xpath("//input[@type='search']");

    public ViewAllServicesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddRowButton() {
        driver.findElement(addRowButtonLocator).click();
    }

    public int countServiceByName(String name) {
        String xpathTitle = String.format("//tr/td[text()='%s']", name);
        List<WebElement> items = driver.findElements(By.xpath(xpathTitle));
        return items.size();
    }

    public void searchServiceByName(String keyword) {
        driver.findElement(searchTextboxLocator).sendKeys(keyword);
    }

}
