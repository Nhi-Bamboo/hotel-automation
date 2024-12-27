package Gwesty.Page.AdminPage;

import Gwesty.Model.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddRoomPage {
    By roomNumberTextBoxLocator = By.name("roomnumber");
    By roomTypeTextBoxLocator = By.name("roomType");
    By floorTextBoxLocator = By.name("floor");
    By statusCheckBoxLocator = By.xpath("//span[@class='slider aqua round']");
    By descriptionTextBoxLocator = By.id("education");
    By submitButtonLocator = By.xpath("//button[@type='submit']");

    public AddRoomPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterRoomNumber(int rn){
        driver.findElement(roomNumberTextBoxLocator).clear();
        driver.findElement(roomNumberTextBoxLocator).sendKeys(String.valueOf(rn));
    }

    public void selectRoomType(String roomType){
        driver.findElement(roomTypeTextBoxLocator).click();
        String xpath = String.format("//li[@class='mdl-menu__item'][text()='%s']",roomType);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enterFloor (int floor){
        driver.findElement(floorTextBoxLocator).clear();
        driver.findElement(floorTextBoxLocator).sendKeys(String.valueOf(floor));
    }

    public void setStatus(boolean status) {
        WebElement checkbox = driver.findElement(statusCheckBoxLocator);
        boolean isCurrentlyActive = checkbox.isSelected();

        if (isCurrentlyActive != status) {
            checkbox.click();
        }
    }

    public void enterDescription(String desc){
        driver.findElement(descriptionTextBoxLocator).sendKeys(desc);
    }

    public void clickSubmitButton(){
        driver.findElement(submitButtonLocator).click();
    }


    public void addRoomInformation(Room room){
        enterRoomNumber(room.getRoomNo());
        selectRoomType(room.getType());
        enterFloor(room.getFloor());
        setStatus(room.isStatus());
        enterDescription(room.getDesc());
        clickSubmitButton();
    }



}
