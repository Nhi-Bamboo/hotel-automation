package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewAllCreditCardPage {
    WebDriver driver;
    By searchTextboxLocator = By.xpath("//input[@type='search']");
    By creditCardNumberLocator = By.xpath("//tr/td[1]");
    By ownerNameLocator = By.xpath("//tr/td[2]");
    By expiryDateLocator = By.xpath("//tr/td[3]");
    By balanceLocator = By.xpath("//tr/td[4]");
    By addNewButtonLocator = By.id("addRow");
    By editButtonLocator = By.xpath("//tr/td[5]/a");

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

    //get in All CreditCard
    public String getCreditCardNumber(int i) {
        return driver.findElements(creditCardNumberLocator).get(i-1).getText();
    }

    public String getOwnerName(int i) {
        return driver.findElements(ownerNameLocator).get(i-1).getText();
    }

    public String getExpiryDate(int i) {
        return driver.findElements(expiryDateLocator).get(i-1).getText();
    }

    // Method to get expiry month
    public int getExpiryMonth(int i) {
        String date = driver.findElements(expiryDateLocator).get(i-1).getText();
        return Integer.parseInt(date.split("/")[0]); // Extract month
    }

    // Method to get expiry year
    public int getExpiryYear(int i) {
        String date = driver.findElements(expiryDateLocator).get(i-1).getText();
        return Integer.parseInt(date.split("/")[1]); // Extract year
    }

    public double getBalance(int i) {
        return Double.parseDouble(driver.findElements(balanceLocator).get(i-1).getText());
    }

    public void clickAddNewButton () {
        driver.findElement(addNewButtonLocator).click();
    }

    public void openDetailCreditCardByIndex(int i){
        List<WebElement> list = driver.findElements(editButtonLocator);
        list.get(i-1).click();
    }

    public CreditCard getCreditCardByIndex(int i) {
        CreditCard c = new CreditCard();
        c.setNumber(getCreditCardNumber(i));
        c.setName(getOwnerName(i));
        c.setMonth(getExpiryMonth(i));
        c.setYear(getExpiryYear(i));
        c.setBalance(getBalance(i));
        return c;
    }


}
