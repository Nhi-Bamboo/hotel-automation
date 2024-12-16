package Gwesty.Page.AdminPage;

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
    By searchTextboxLocator = By.xpath("//input[@type='search']");
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
    public void enterBalance(float number) {
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

    public String formatCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber.length()>16) {
            creditCardNumber = creditCardNumber.substring(0,16);
        }
        creditCardNumber = creditCardNumber.replaceAll("\\D", "");
        String cardNumber = "";
        for (int i=0;i<creditCardNumber.length();i=i+4) {
            if (i + 4 <= creditCardNumber.length()) {
                cardNumber += creditCardNumber.substring(i, i + 4) + " ";
            } else {
                cardNumber += creditCardNumber.substring(i); // Xử lý phần dư
            }
        }
        return cardNumber;
    }


}
