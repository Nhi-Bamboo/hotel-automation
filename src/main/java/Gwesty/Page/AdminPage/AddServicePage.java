package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import Gwesty.Model.Service;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddServicePage {
    WebDriver driver;
    By serviceNameTextBoxLocator = By.name("serviceName");
    By unitTextboxLocator = By.name("unit");
    By priceTextboxLocator = By.name("price");
    By descriptionTextboxLocator = By.xpath("//textarea[@name='description']");
    By submitButtonLocator = By.xpath("//button[@type='submit']");

    public AddServicePage(WebDriver driver) {
        this.driver = driver;
    }

    private void enterServiceName(String name) {
        driver.findElement(serviceNameTextBoxLocator).sendKeys(name);
    }

    private void enterUnit(String unit) {
        driver.findElement(unitTextboxLocator).sendKeys(unit);
    }

    private void enterPrice(double price) {
        String p = String.valueOf(price);
        driver.findElement(priceTextboxLocator).sendKeys(p);
    }

    private void enterDescription(String description) {
        driver.findElement(descriptionTextboxLocator).sendKeys(description);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void addServiceInformation(Service service) {
        Allure.step("Add Service information");
        enterServiceName(service.getName());
        enterUnit(service.getUnit());
        enterPrice(service.getPrice());
        enterDescription(service.getDescription());
    }
}
