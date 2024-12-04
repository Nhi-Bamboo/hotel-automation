package Gwesty.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    By userNameTextBoxLocator = By.name("email");
    By passwordTextBoxLocator = By.id("password");
    By signInButtonLocator = By.xpath("//input[@value='Sign In']");
    By titleOfHomepageLocator = By.xpath("//h1[text()='Best Hotel to stay']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterUserName(String un){
        driver.findElement(userNameTextBoxLocator).sendKeys(un);
    }

    public void enterPassword(String pass){
        driver.findElement(passwordTextBoxLocator).sendKeys(pass);
    }

    public void clickSignInButton(){
        driver.findElement(signInButtonLocator).click();
    }

    public void login(String username, String password){
        enterUserName(username);
        enterPassword(password);
        clickSignInButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleOfHomepageLocator));
    }
}
