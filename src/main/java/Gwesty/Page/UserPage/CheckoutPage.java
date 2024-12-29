package Gwesty.Page.UserPage;

import Gwesty.Model.CreditCard;
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
         enterCardNumberBox(cardNumber);
         enterNameOnCardBox(nameCard);
         enterExpiryDateBox(expiryDate);
         enterCvvNumberBox(cvv);
         clickPayNowButton();
    }

    public void enterCreditCardInformation(CreditCard creditCard){
        enterCardNumberBox(creditCard.getNumber());
        enterNameOnCardBox(creditCard.getName());
        enterExpiryDateByMonthAndYear(creditCard);
        enterCvvNumberBox(creditCard.getCvv());
        clickPayNowButton();
    }

}
