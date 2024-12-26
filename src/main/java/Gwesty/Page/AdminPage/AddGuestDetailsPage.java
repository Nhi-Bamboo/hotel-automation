package Gwesty.Page.AdminPage;

import Gwesty.Model.GuestInRoom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddGuestDetailsPage {
    WebDriver driver;
    WebDriverWait wait;
    String month="";
    String year="";
    int room;
    String date="";
    GuestInRoom guestInRoom;

    By fullNameTextBoxLocator = By.id("name");
    By genderTextBoxLocator = By.name("sex");
    By dateOfBirthDatepickerLocator = By.id("dateOfBirth");
    By okButtonOfDatePickerLocator = By.xpath("//div[@class='dtp-buttons']/button[text()='OK']");
    By roomNumberTextBoxLocator = By.name("roomNum");
    By firstRoomNumberLocator = By.xpath("//ul[@data-mdl-for='sample2']/li");
    By addressTextBoxLocator = By.name("address");
    By identifyTypeLocator = By.name("idType");
    By identifyNumberTextBoxLocator = By.name("idNo");
    By submitButtonLocator = By.xpath("//button[@type='submit']");
    By monthOfCalendarLocator = By.xpath("//div[@class='dtp-picker-month']");
    By dateOfCalendarLocator = By.xpath("//div[@class='dtp-actual-num']");
    By guestInRoomNavLocator = By.xpath("//ul[@id='nav']/li/a[text()='Guest In Room']");


    public AddGuestDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFullName(String name) {
        driver.findElement(fullNameTextBoxLocator).sendKeys(name);
    }
    public void selectGender(String gender) {
        driver.findElement(genderTextBoxLocator).click();
        String xpath = String.format("//ul/li[text()='%s']",gender);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectCurrentDate() {
        driver.findElement(dateOfBirthDatepickerLocator).click();
//        String d = String.valueOf(date);
//        String xpath = String.format("//td[@data-date='%s']",d);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(monthOfCalendarLocator));
        month = driver.findElement(monthOfCalendarLocator).getText().replaceAll("\\d", "");
        year = driver.findElement(monthOfCalendarLocator).getText().replaceAll("\\D", "");
        date = driver.findElement(dateOfCalendarLocator).getText();

//        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(okButtonOfDatePickerLocator).click();

    }

    private void selectFirstRoomNumber() {
        driver.findElement(roomNumberTextBoxLocator).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstRoomNumberLocator));
        room = Integer.parseInt(driver.findElement(firstRoomNumberLocator).getText());
        driver.findElement(firstRoomNumberLocator).click();
    }

    private void enterAddress(String address) {
        driver.findElement(addressTextBoxLocator).sendKeys(address);
    }

    private void selectIdentifyType(String type) {
        driver.findElement(identifyTypeLocator).click();
        String xpath = String.format("//ul[@data-mdl-for='sample1']/li[text()='%s']",type.toUpperCase());
        driver.findElement(By.xpath(xpath)).click();
    }

    private void enterIdentifyNumber(int num) {
        String number = String.valueOf(num);
        driver.findElement(identifyNumberTextBoxLocator).sendKeys(number);
    }

    public void enterGuestInRoomInformation(String name, String gender, int date, String address, String type, int num) {
        enterFullName(name);
        selectGender(gender);
        selectCurrentDate();
        selectFirstRoomNumber();
        enterAddress(address);
        selectIdentifyType(type);
        enterIdentifyNumber(num);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestInRoomNavLocator));
    }

    public String getMonth() {
        guestInRoom = new GuestInRoom();
        return guestInRoom.convertMonthToNumber(month);
    }

    public String getYear() {
        return year.trim();
    }

    public int getRoom() {
        return room;
    }

    public String getDate() {
        return date;
    }

    public void addGuestInRoomInformation(GuestInRoom guest) {
        enterFullName(guest.getFullName());
        selectGender(guest.getGender());
        selectCurrentDate();
        selectFirstRoomNumber();
        enterAddress(guest.getAddress());
        selectIdentifyType(guest.getType());
        enterIdentifyNumber(guest.getId());
    }

}

