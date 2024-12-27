package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCreditCardPage {
    WebDriver driver;
    By creditCardNumberTextboxLocator = By.id("number");
    By ownerNameTextboxLocator = By.id("ownerName");
    By expiryMonthSelectorBoxLocator = By.id("list1");
    By expiryYearSelectorBoxLocator = By.id("expiryYear");
    By cvvCodeTextboxLocator = By.id("cvvcode");
    By balanceTextboxLocator = By.id("balance");
    By submitButtonLocator = By.xpath("//button[text()='Submit']");
    By pageTitleLocator = By.xpath("//div[text()='All CreditCard']");
    By allCreditCardTabLocator = By.xpath("//a[@class='parent-item'][text()='CreditCard']");

    public AddCreditCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCreditCardNumber(String number) {
        driver.findElement(creditCardNumberTextboxLocator).sendKeys(number);
    }

    public void enterOwnerName(String name) {
        driver.findElement(ownerNameTextboxLocator).sendKeys(name);
    }

    public void selectExpiryMonth(int month) {
        String m = String.valueOf(month);
        String xpathMonth = String.format("//ul[@data-mdl-for='list1']/li[@data-val='%s']",m);
        driver.findElement(expiryMonthSelectorBoxLocator).click();
        driver.findElement(By.xpath(xpathMonth)).click();
    }
    public void selectExpiryYear(int year) {
        driver.findElement(expiryYearSelectorBoxLocator).clear();
        String y = String.valueOf(year);
        driver.findElement(expiryYearSelectorBoxLocator).sendKeys(y);
    }
    public void enterCVVCode(int cvv) {
        String c = String.valueOf(cvv);
        driver.findElement(cvvCodeTextboxLocator).sendKeys(c);
    }
    public void enterBalance(double number) {
        driver.findElement(balanceTextboxLocator).clear();
        String n = String.valueOf(number);
        driver.findElement(balanceTextboxLocator).sendKeys(n);
    }
    public void enterCreditCardInformation(String number, String name,int month, int year, int cvv, float balance) {
        enterCreditCardNumber(number);
        enterOwnerName(name);
        selectExpiryMonth(month);
        selectExpiryYear(year);
        enterCVVCode(cvv);
        enterBalance(balance);
    }
    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated((pageTitleLocator)));
    }

    public void addCreditCardInformation(CreditCard card) {
        enterCreditCardNumber(card.getNumber());
        enterOwnerName(card.getName());
        selectExpiryMonth(card.getMonth());
        selectExpiryYear(card.getYear());
        enterBalance(card.getBalance());
    }
    //get attribute
    public String getCreditCardNumber(){
        return driver.findElement(creditCardNumberTextboxLocator).getAttribute("value");
    }

    public String getOwnerName(){
        return driver.findElement(ownerNameTextboxLocator).getAttribute("value");
    }

    public int getExpiryMonth(){
        return Integer.parseInt(driver.findElement(expiryMonthSelectorBoxLocator).getAttribute("value"));
    }

    public int getExpiryYear(){
        return Integer.parseInt(driver.findElement(expiryYearSelectorBoxLocator).getAttribute("value"));
    }

    public int getCvvCode(){
        return Integer.parseInt(driver.findElement(cvvCodeTextboxLocator).getAttribute("value"));
    }

    public double getBalance(){
        return Double.parseDouble(driver.findElement(balanceTextboxLocator).getAttribute("value"));
    }

    public void openAllCreditCardTab(){
        driver.findElement(allCreditCardTabLocator).click();
    }
}
