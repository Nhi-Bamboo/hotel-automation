package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookNowPage {
    By nameTextBoxLocator = By.id("name");
    By emailTextBoxLocator = By.id("email");
    By phoneTextBoxLocator = By.id("phone");
    By addressTextBoxLocator = By.id("address");
    By checkBoxAgreeLocator = By.xpath("//*[@class='custom-control custom-checkbox']");
    By submitButtonLocator = By.xpath("//input[@value='Submit']");
    By bookNowLabelLocator = By.xpath("//h2[text()='Book Now']");

    public BookNowPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterNameTextBox(String name){
        driver.findElement(nameTextBoxLocator).clear();
        driver.findElement(nameTextBoxLocator).sendKeys(name);
    }

    public void enterEmailTextBox(String email){
        driver.findElement(emailTextBoxLocator).clear();
        driver.findElement(emailTextBoxLocator).sendKeys(email);
    }

    public void enterPhoneTextBox(String phone){
        driver.findElement(phoneTextBoxLocator).clear();
        driver.findElement(phoneTextBoxLocator).sendKeys(phone);
    }

    public void enterAddressTextBox(String address){
        driver.findElement(addressTextBoxLocator).clear();
        driver.findElement(addressTextBoxLocator).sendKeys(address);
    }

    public void checkCheckBoxAgree(){
        if (!driver.findElement(checkBoxAgreeLocator).isSelected()){
            driver.findElement(checkBoxAgreeLocator).click();
        }
    }

    public void clickSubmitButton(){
        driver.findElement(submitButtonLocator).click();
    }

    public void addGuestInformation(String name, String e, String phone, String address){
        enterNameTextBox(name);
        enterEmailTextBox(e);
        enterPhoneTextBox(phone);
        enterAddressTextBox(address);
        checkCheckBoxAgree();
        clickSubmitButton();
    }

    public boolean isBookNowLabelDisplayed(){
        return driver.findElement(bookNowLabelLocator).isDisplayed();
    }

    public void enterBookingNowInformation(String name, String email, String phone, String address) {
        enterNameTextBox(name);
        enterEmailTextBox(email);
        enterPhoneTextBox(phone);
        enterAddressTextBox(address);

    }
}
