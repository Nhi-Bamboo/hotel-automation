package Gwesty.Page.AdminPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickMenu(String option) {
        String xpathMenu = String.format("//span[@class='title'][text()='%s']",option);
        driver.findElement(By.xpath(xpathMenu)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void clickSubMenu(String option) {
        String xpathSubMenu = String.format("//span[@class='title'][normalize-space(text())='%s']",option);
        driver.findElement(By.xpath(xpathSubMenu)).click();
    }

    public void openAllRoomTypePage(){
        Allure.step("Open View All Room Types Page");
        clickMenu("Room Types");
        clickSubMenu("View All Room Types");
    }

    @Step("Open Booking Page")
    public void openBookingPage() {
        clickMenu("Booking");
    }

    public void openAddRoomPage(){
        Allure.step("Open Add Room Page");
        clickMenu("Rooms");
        clickSubMenu("Add Room");
    }

    public void openViewAllServices() {
        Allure.step("Open View All Services Page");
        clickMenu("Service");
        clickSubMenu("View All Services");
    }

    public void openAddPromotionPage() {
        Allure.step("Open Add Promotion Page");
        clickMenu("Promotion");
        clickSubMenu("Add Promotion");
    }

    public void openAddCreditCard(){
        Allure.step("Open Add Credit Card Page");
        clickMenu("CreditCard");
        clickSubMenu("Add CreditCard");
    }

    public void openViewAllCreditCard() {
        Allure.step("Open View All Credit Card Page");
        clickMenu("CreditCard");
        clickSubMenu("View All CreditCard");
    }
}
