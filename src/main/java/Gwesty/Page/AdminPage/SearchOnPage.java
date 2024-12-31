package Gwesty.Page.AdminPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchOnPage {
    By searchTextBoxLocator = By.xpath("//input[@type='search']");

    public SearchOnPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Step("Search")
    public void searchByNumber(int roomNo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextBoxLocator));
        driver.findElement(searchTextBoxLocator).sendKeys(String.valueOf(roomNo));

    }

    @Step("Search")
    public void searchByString(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextBoxLocator));
        driver.findElement(searchTextBoxLocator).sendKeys(text);

    }




}
