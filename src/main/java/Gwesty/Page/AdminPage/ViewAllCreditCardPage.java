package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewAllCreditCardPage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");

    public ViewAllCreditCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchByCreditCardNumber(String number) {
        driver.findElement(searchTextboxLocator).sendKeys(number);
    }

    public boolean isCreditCardNumberDisplayed(String number) {
        String xpathNumber = String.format("//td[text()='%s']",number);
        return driver.findElement(By.xpath(xpathNumber)).isDisplayed();
    }
}
