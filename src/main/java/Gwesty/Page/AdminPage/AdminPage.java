package Gwesty.Page.AdminPage;

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
        clickMenu("Room Types");
        clickSubMenu("View All Room Types");
    }

    public void openBookingPage() {
        clickMenu("Booking");
    }

    public void openAddRoomPage(){
        clickMenu("Rooms");
        clickSubMenu("Add Room");
    }

    public void openViewAllCreditCard() {
        clickMenu("CreditCard");
        clickSubMenu("View All CreditCard");
    }

    public void openViewAllServices() {
        clickMenu("Service");
        clickSubMenu("View All Services");
    }

    public void openAddPromotionPage() {
        clickMenu("Promotion");
        clickSubMenu("Add Promotion");
    }

    public void openAddCreditCard(){
        clickMenu("CreditCard");
        clickSubMenu("Add CreditCard");
    }
}
