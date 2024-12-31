package Gwesty.Page.AdminPage;

import Gwesty.Model.GuestInRoom;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

public class AddGuestDetailsPage {
    WebDriver driver;
    WebDriverWait wait;
    int room;

    By fullNameTextBoxLocator = By.id("name");
    By genderTextBoxLocator = By.name("sex");
    By dateOfBirthDatepickerLocator = By.id("dateOfBirth");
    By roomNumberTextBoxLocator = By.name("roomNum");
    By firstRoomNumberLocator = By.xpath("//ul[@data-mdl-for='sample2']/li");
    By addressTextBoxLocator = By.name("address");
    By identifyTypeLocator = By.name("idType");
    By identifyNumberTextBoxLocator = By.name("idNo");
    By submitButtonLocator = By.xpath("//button[@type='submit']");

    By monthAndYearLocator = By.xpath("//div[@class='dtp-picker-month']");
    By yearLocator = By.xpath("//div[@class='dtp']//div[@class='dtp-actual-year p80']");
    By guestInRoomNavLocator = By.xpath("//ul[@id='nav']/li/a[text()='Guest In Room']");
    By chevronLeftYearLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-year-before']//i[text()='chevron_left']");
    By chevronRightYearLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-year-after']//i[text()='chevron_right']");
    By chevronLeftMonthLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-month-before']//i[text()='chevron_left']");
    By chevronRightMonthLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-month-after']//i[text()='chevron_right']");
    By oKButtonLocator = By.xpath("//div[@class='dtp']//button[text()='OK']");


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

    public int getMonthNumberFromAbbreviation(String month) {
        // Chuyển đổi trực tiếp từ tên tháng đầy đủ (viết hoa) sang giá trị tháng
        return Month.valueOf(month).getValue();
    }

    public void selectCurrentDate(String date) {
        // Chia nhỏ ngày, tháng, năm từ chuỗi "yyyy-MM-dd"
        String[] parts = date.split("-");
        int targetYear = Integer.parseInt(parts[0]);
        int targetMonth = Integer.parseInt(parts[1]);
        int targetDay = Integer.parseInt(parts[2]);
        driver.findElement(dateOfBirthDatepickerLocator).click();
        while (true) {
            // Lấy năm và tháng hiện tại từ popup
            int currentYear = Integer.parseInt(driver.findElement(yearLocator).getText());
            String currentMonthString = driver.findElement(monthAndYearLocator).getText();
            currentMonthString = currentMonthString.replaceAll("\\d", "").trim();
            int currentMonth = getMonthNumberFromAbbreviation(currentMonthString);

            // Kiểm tra nếu cần điều chỉnh năm
            if (currentYear != targetYear) {
                if (currentYear > targetYear) {
                    driver.findElement(chevronLeftYearLocator).click(); // Nút "<" của năm
                } else {
                    driver.findElement(chevronRightYearLocator).click(); // Nút ">" của năm
                }
            }

            // Kiểm tra nếu cần điều chỉnh tháng
            else if (currentMonth != targetMonth) {
                if (currentMonth > targetMonth) {
                    driver.findElement(chevronLeftMonthLocator).click(); // Nút "<" của tháng
                } else {
                    driver.findElement(chevronRightMonthLocator).click(); // Nút ">" của tháng
                }
            }
            // Nếu năm và tháng đã đúng, thoát vòng lặp
            else {
                break;
            }
        }

        // Chọn ngày
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dtp']//td[@data-date='" + targetDay + "']")));
        driver.findElement(By.xpath("//div[@class='dtp']//td[@data-date='" + targetDay + "']")).click();

        // Nhấn OK để xác nhận
        driver.findElement(oKButtonLocator).click();

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



    public void clickSubmitButton() {
        Allure.step("Click Submit Button");
        driver.findElement(submitButtonLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestInRoomNavLocator));
    }


    public int getRoom() {
        return room;
    }

    public void addGuestInRoomInformation(GuestInRoom guest) {
        Allure.step("Enter Guest In Room information");
        enterFullName(guest.getFullName());
        selectGender(guest.getGender());
        selectCurrentDate(guest.getDateOfBirth());
        selectFirstRoomNumber();
        enterAddress(guest.getAddress());
        selectIdentifyType(guest.getType());
        enterIdentifyNumber(guest.getId());
    }

}

