package Gwesty.Page.AdminPage;

import Gwesty.Model.CreditCard;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCreditCardPage {
    WebDriver driver;
    By creditCardNumberTextboxLocator = By.id("number");
    By ownerNameTextboxLocator = By.id("ownerName");
    By expiryMonthSelectorBoxLocator = By.xpath("//input[@name='expiryMonth']");
    By expiryYearTextBoxLocator = By.id("expiryYear");
    By cvvCodeTextboxLocator = By.id("cvvcode");
    By balanceTextboxLocator = By.id("balance");
    By submitButtonLocator = By.xpath("//button[text()='Submit']");
    By pageTitleLocator = By.xpath("//div[text()='All CreditCard']");

    public AddCreditCardPage(WebDriver driver) {
        this.driver = driver;
    }

    private void enterCreditCardNumber(String number) {
        driver.findElement(creditCardNumberTextboxLocator).sendKeys(number);
    }

    private void enterOwnerName(String name) {
        driver.findElement(ownerNameTextboxLocator).sendKeys(name);
    }

    private void enterExpiryMonth(int month) {
        driver.findElement(expiryMonthSelectorBoxLocator).clear();
        driver.findElement(expiryMonthSelectorBoxLocator).sendKeys(String.valueOf(month));
    }
    private void enterExpiryYear(int year) {
        driver.findElement(expiryYearTextBoxLocator).clear();
        driver.findElement(expiryYearTextBoxLocator).sendKeys(String.valueOf(year));
    }
    private void enterCVVCode(int cvv) {
        driver.findElement(cvvCodeTextboxLocator).clear();
        driver.findElement(cvvCodeTextboxLocator).sendKeys(String.valueOf(cvv));
    }
    private void enterBalance(double balance) {
        driver.findElement(balanceTextboxLocator).clear();
        driver.findElement(balanceTextboxLocator).sendKeys(String.valueOf(balance));
    }
    @Step("Click Submit Button")
    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated((pageTitleLocator)));
    }
    @Step("Enter credit card information")
    public void addCreditCardInformation(CreditCard card) {
        Allure.step(String.format("Enter Credit Card Number: %s",card.getNumber()));
        enterCreditCardNumber(card.getNumber());

        Allure.step(String.format("Enter Owner Name: %s",card.getName()));
        enterOwnerName(card.getName());

        Allure.step(String.format("Enter Expiry Month: %d",card.getMonth()));
        enterExpiryMonth(card.getMonth());

        Allure.step(String.format("Enter Expiry Year: %d",card.getYear()));
        enterExpiryYear(card.getYear());

        Allure.step(String.format("Enter CVV Code: %d",card.getCvv()));
        enterCVVCode(card.getCvv());

        Allure.step(String.format("Enter Balance: %f",card.getBalance()));
        enterBalance(card.getBalance());

        Allure.step("Click Submit Button");
        clickSubmitButton();
    }


}
