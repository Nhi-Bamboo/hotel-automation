package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewAllCreditCardPage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By creditCardNumberLocator = By.xpath("//tr[@class='gradeX odd']/td[1]");
    By ownerNameLocator = By.xpath("//tr[@class='gradeX odd']/td[2]");
    By expiryDateLocator = By.xpath("//tr[@class='gradeX odd']/td[3]");
    By balanceLocator = By.xpath("//tr[@class='gradeX odd']/td[4]");
    By addNewButtonLocator = By.id("addRow");

    public ViewAllCreditCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchByCreditCard(String number) {
        driver.findElement(searchTextboxLocator).sendKeys(number);
    }

    public boolean isCreditCardNumberDisplayed(String number) {
        String xpathNumber = String.format("//td[text()='%s']",number);
        return driver.findElement(By.xpath(xpathNumber)).isDisplayed();
    }

    public String getCreditCardNumber() {
        return driver.findElement(creditCardNumberLocator).getText();
    }

    public String getOwnerName() {
        return driver.findElement(ownerNameLocator).getText();
    }

    public String getExpiryDate() {
        return driver.findElement(expiryDateLocator).getText();
    }

    public float getBalance() {
        return Float.parseFloat(driver.findElement(balanceLocator).getText());
    }

    public void clickAddNewButton () {
        driver.findElement(addNewButtonLocator).click();
    }
}
