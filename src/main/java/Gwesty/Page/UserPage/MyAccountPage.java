package Gwesty.Page.UserPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {

    By fullNameTextBoxLocator = By.id("name");
    By emailTextBoxLocator = By.id("email");
    By phoneTextBoxLocator = By.id("phone");
    By addressTextBoxLocator = By.id("address");
    By updateButtonLocator = By.xpath("//button[text()='Update']");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterFullName(String name){
        driver.findElement(fullNameTextBoxLocator).clear();
        driver.findElement(fullNameTextBoxLocator).sendKeys(name);
    }

    public void enterEmail(String email){
        driver.findElement(emailTextBoxLocator).clear();
        driver.findElement(emailTextBoxLocator).sendKeys(email);
    }

    public void enterPhone(String phone){
        driver.findElement(phoneTextBoxLocator).clear();
        driver.findElement(phoneTextBoxLocator).sendKeys(phone);
    }

    public void enterAddress(String address){
        driver.findElement(addressTextBoxLocator).clear();
        driver.findElement(addressTextBoxLocator).sendKeys(address);
    }

    public void clickUpdateButton(){
        driver.findElement(updateButtonLocator).click();
    }

    @Step("Edit Account Information")
    public void editAccountInformation( String name, String email, String phone, String address) {
        Allure.step(String.format("Update Full Name: %s",name));
        enterFullName(name);

        Allure.step(String.format("Update Email: %s",email));
        enterEmail(email);

        Allure.step(String.format("Update Phone: %s",phone));
        enterPhone(phone);

        Allure.step(String.format("Update Address: %s",address));
        enterAddress(address);

        Allure.step("Click Update Button");
        clickUpdateButton();
    }

    public String getFullNameTextBoxValue(){
        return driver.findElement(fullNameTextBoxLocator).getAttribute("value");
    }

    public String getEmailTextBoxValue(){
        return driver.findElement(emailTextBoxLocator).getAttribute("value");
    }

    public String getPhoneTextBoxValue(){
        return driver.findElement(phoneTextBoxLocator).getAttribute("value");
    }

    public String getAddressTextBoxValue(){
        return driver.findElement(addressTextBoxLocator).getAttribute("value");
    }

}
