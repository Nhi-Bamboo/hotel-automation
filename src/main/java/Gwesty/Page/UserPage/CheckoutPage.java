package Gwesty.Page.UserPage;

import Gwesty.Model.CreditCard;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    By cardNumberBoxLocator = By.id("cardNumber");
    By nameOnCardBoxLocator = By.id("ownerName");
    By expiryDateBoxLocator = By.name("expiry");
    By cvvNumberBoxLocator = By.id("cvvcode");
    By payNowButtonLocator = By.xpath("//input[@value='Pay Now']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

     public void enterCardNumberBox(String cardNumber){
        driver.findElement(cardNumberBoxLocator).sendKeys(cardNumber);
    }

    public void enterNameOnCardBox(String name){
        driver.findElement(nameOnCardBoxLocator).sendKeys(name);
    }

    public void enterExpiryDateBox(String date){
        driver.findElement(expiryDateBoxLocator).sendKeys(date);
    }

    public void enterExpiryDateByMonthAndYear(CreditCard creditCard){
        driver.findElement(expiryDateBoxLocator).sendKeys(String.format("%02d/%d",creditCard.getMonth(),creditCard.getYear()));
    }

    public void enterCvvNumberBox(int cvv){
        driver.findElement(cvvNumberBoxLocator).clear();
        driver.findElement(cvvNumberBoxLocator).sendKeys(String.valueOf(cvv));
    }

    public void clickPayNowButton(){
        driver.findElement(payNowButtonLocator).click();
    }

    public void paymentByCreditCard(String cardNumber, String nameCard, String expiryDate, int cvv){
        Allure.step(String.format("Enter Card Number: %s",cardNumber));
        enterCardNumberBox(cardNumber);

        Allure.step(String.format("Enter Name On Card: %s",nameCard));
        enterNameOnCardBox(nameCard);

        Allure.step(String.format("Enter Expiry Date: %s",expiryDate));
        enterExpiryDateBox(expiryDate);

        Allure.step(String.format("Enter Cvv Number: %s",cvv));
        enterCvvNumberBox(cvv);

        Allure.step("Click Pay Now Button");
        clickPayNowButton();
    }

    public void enterCreditCardInformation(CreditCard creditCard){
        Allure.step(String.format("Enter Card Number: %s",creditCard.getNumber()));
        enterCardNumberBox(creditCard.getNumber());

        Allure.step(String.format("Enter Name On Card: %s",creditCard.getName()));
        enterNameOnCardBox(creditCard.getName());

        Allure.step(String.format("Enter Expiry Date: %s",creditCard));
        enterExpiryDateByMonthAndYear(creditCard);

        Allure.step(String.format("Enter Cvv Number: %s",creditCard.getCvv()));
        enterCvvNumberBox(creditCard.getCvv());

        Allure.step("Click Pay Now Button");
        clickPayNowButton();
    }

}
