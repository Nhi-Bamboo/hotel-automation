package Gwesty.Page.UserPage;

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

    public void enterCvvNumberBox(String cvv){
        driver.findElement(cvvNumberBoxLocator).sendKeys(cvv);
    }

    public void clickPayNowButton(){
        driver.findElement(payNowButtonLocator).click();
    }

    public void paymentByCreditCard(String cardNumber, String nameCard, String expiryDate, String cvv){
         enterCardNumberBox(cardNumber);
         enterNameOnCardBox(nameCard);
         enterExpiryDateBox(expiryDate);
         enterCvvNumberBox(cvv);
         clickPayNowButton();
    }

}
