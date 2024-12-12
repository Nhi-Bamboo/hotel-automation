package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingDetailPage {
    WebDriver driver;
    By makeConfirmButtonLocator = By.xpath("//div/a[text()=' Make Confirm']");
    By checkOutButtonLocator = By.xpath("//div/a[text()=' CHECKOUT']");
    By roomCheckBoxLocator = By.xpath("//div[@class='custom-control custom-checkbox']/label");
    By submitButtonLocator = By.id("btn-submit");
    By nextButtonLocator = By.id("btn-send");
    By submitButtonOfPaymentFormLocator = By.xpath("//form[@id='payment-form']//button[@type='submit']");
    By payMethodLocator = By.id("payMethod");
    By cardNumberTextboxLocator = By.name("cardNumber");
    By nameTextboxLocator = By.name("ownerName");
    By expiryDateTextboxLocator = By.name("expiry");
    By cvvNumberTextboxLocator = By.name("cvvcode");
    By successButtonLocator = By.xpath("//div/a[text()='SUCCESS']");
    public BookingDetailPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickMakeConfirmButton() {
        driver.findElement(makeConfirmButtonLocator).click();
    }
    public boolean isCheckOutButtonDisplayed() {
        return driver.findElement(checkOutButtonLocator).isDisplayed();
    }
    public void clickCheckOutButton() {
        driver.findElement(checkOutButtonLocator).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated((submitButtonLocator)));

    }
    public void clickRoomCheckBox() {
        driver.findElement(roomCheckBoxLocator).click();
    }
    public void clickNextButton() {
        driver.findElement(nextButtonLocator).click();
    }
    public void selectPaymentMethod(String option) {
       Select dropdown = new Select(driver.findElement(payMethodLocator));
       dropdown.selectByVisibleText(option);
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated((cardNumberTextboxLocator)));
    }
    public void enterCardNumber(String number){
        driver.findElement(cardNumberTextboxLocator).sendKeys(number);
    }
    public void enterName(String name) {
        driver.findElement(nameTextboxLocator).sendKeys(name);
    }
    public void enterExpiryDate(String date) {
        driver.findElement(expiryDateTextboxLocator).sendKeys(date);
    }
    public void enterCVVNumber(int cvv) {
        String n = String.valueOf(cvv);
        driver.findElement(cvvNumberTextboxLocator).sendKeys(n);
    }
    public void enterPaymentInformation(String number, String name, String date, int cvv) {
        enterCardNumber(number);
        enterName(name);
        enterExpiryDate(date);
        enterCVVNumber(cvv);
    }
    public void clickSubMitButtonOfPaymentForm() {
        driver.findElement(submitButtonOfPaymentFormLocator).click();
    }
    public boolean isSuccessButtonDisplayed() {
        return driver.findElement(successButtonLocator).isDisplayed();
    }
}
