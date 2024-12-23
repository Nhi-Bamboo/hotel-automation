package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddServicePage {
    WebDriver driver;
    By serviceNameTextBoxLocator = By.name("serviceName");
    By unitTextboxLocator = By.name("unit");
    By priceTextboxLocator = By.name("price");
    By descriptionTextboxLocator = By.name("description");
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

    private void enterPrice(int price) {
        String p = String.valueOf(price);
        driver.findElement(priceTextboxLocator).sendKeys(p);
    }

    private void enterDescription(String description) {
        driver.findElement(descriptionTextboxLocator).sendKeys(description);
    }

    public void enterServiceInformation(String name, String unit, int price, String description) {
        enterServiceName(name);
        enterUnit(unit);
        enterPrice(price);
        enterDescription(description);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }
}
