package Gwesty.Page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {
    By editPhotoButtonLocator = By.xpath("//label[@for='upload']");
    By fileInputLocator = By.name("fileDatas");
    By fullNameTextBoxLocator = By.id("name");
    By emailTextBoxLocator = By.id("email");
    By phoneTextBoxLocator = By.id("phone");
    By addressTextBoxLocator = By.id("address");
    By updateButtonLocator = By.xpath("//button[text()='Update']");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void clickEditPhotoButton() {
        driver.findElement(editPhotoButtonLocator).click();
    }

    // Chọn ảnh từ máy tính
    public void chooseImage(String imagePath) {
        driver.findElement(fileInputLocator).sendKeys(imagePath);
    }

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

    public void editAccountInformation(String imagePath, String name, String email, String phone, String address) {
        //edit photo
        clickEditPhotoButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(fileInputLocator));

        chooseImage(imagePath);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(fileInputLocator));

        //edit information
        enterFullName(name);
        enterEmail(email);
        enterPhone(phone);
        enterAddress(address);
        clickUpdateButton();
    }

}
