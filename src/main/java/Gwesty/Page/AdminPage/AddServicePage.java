package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import Gwesty.Model.Service;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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
        Allure.step("Click submit button");
        driver.findElement(submitButtonLocator).click();
    }

    @Step("Add Service information")
    public void addServiceInformation(Service service) {
        Allure.step(String.format("Enter Service name: %s",service.getName()));
        enterServiceName(service.getName());
        Allure.step(String.format("Enter Service unit: %s",service.getUnit()));
        enterUnit(service.getUnit());
        Allure.step(String.format("Enter Service price: %s",service.getPrice()));
        enterPrice(service.getPrice());
        Allure.step(String.format("Enter Service description: %s",service.getDescription()));
        enterDescription(service.getDescription());
    }
}
