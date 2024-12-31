package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import Gwesty.Model.Service;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewAllServicesPage {
    WebDriver driver;
    By addRowButtonLocator = By.id("addRow");
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By serviceNameLocator = By.xpath("//tr/td[1]");
    By unitServiceLocator = By.xpath("//tr/td[2]");
    By priceServiceLocator = By.xpath("//tr/td[3]");
    By descriptionLocator = By.xpath("//tr/td[4]");

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
        Allure.step("Search Service By Name: "+keyword);
        driver.findElement(searchTextboxLocator).sendKeys(keyword);
    }

    public String getServiceName(int i ) {
        return driver.findElements(serviceNameLocator).get(i-1).getText();
    }

    public String getUnit(int i) {
        return driver.findElements(unitServiceLocator).get(i-1).getText();
    }

    public double getPrice(int i) {
        return Double.parseDouble(driver.findElements(priceServiceLocator).get(i-1).getText());
    }

    public String getDescription(int i) {
        return driver.findElements(descriptionLocator).get(i-1).getText();
    }

    public Service getServiceByIndex(int i) {
        Service s = new Service();
        s.setName(getServiceName(i));
        s.setUnit(getUnit(i));
        s.setPrice(getPrice(i));
        s.setDescription(getDescription(i));
        return s;
    }
}
