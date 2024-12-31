package Gwesty.Page.AdminPage;

import Gwesty.Model.Promotion;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPromotionPage {

    By nameFieldLocator = By.name("promname");
    By codeFieldLocator = By.name("code");
    By startDateFieldLocator = By.name("startdate");
    By endDateFieldLocator = By.name("enddate");
    By typeDropdownLocator = By.name("type");
    By valueFieldLocator = By.name("value");
    By descriptionFieldLocator = By.xpath("//textarea[@name='description']");
    By monthLocator = By.xpath("//div[@class='dtp']//div[@class='dtp-actual-month p80']");
    By chevronLeftMonthLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-month-before']//i[text()='chevron_left']");
    By chevronRightMonthLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-month-after']//i[text()='chevron_right']");
    By yearLocator = By.xpath("//div[@class='dtp']//div[@class='dtp-actual-year p80']");
    By chevronLeftYearLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-year-before']//i[text()='chevron_left']");
    By chevronRightYearLocator = By.xpath("//div[@class='dtp']//a[@class='dtp-select-year-after']//i[text()='chevron_right']");
    By oKButtonLocator = By.xpath("//div[@class='dtp']//button[text()='OK']");
    By submitButtonLocator = By.xpath("//button[@type='submit']//span[@class='mdl-button__ripple-container']");

    WebDriver driver;

    public AddPromotionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterNamePromotion(String name){
        driver.findElement(nameFieldLocator).sendKeys(name);
    }

    public void enterCodePromotion(String code){
        driver.findElement(codeFieldLocator).sendKeys(code);
    }

    private int getMonthNumberFromAbbreviation(String monthAbbreviation) {
        switch (monthAbbreviation) {
            case "JAN": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "APR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AUG": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DEC": return 12;
            default:
                System.out.println("Invalid month abbreviation: " + monthAbbreviation);
                return 0;
        }
    }

    private void selectDateFromCalendar(String date) {
        // Chia nhỏ ngày, tháng, năm từ chuỗi "yyyy-MM-dd"
        String[] parts = date.split("-");
        int targetYear = Integer.parseInt(parts[0]);
        int targetMonth = Integer.parseInt(parts[1]);
        int targetDay = Integer.parseInt(parts[2]);

        while (true) {
            // Lấy năm và tháng hiện tại từ popup
            int currentYear = Integer.parseInt(driver.findElement(yearLocator).getText());
            String currentMonthString = driver.findElement(monthLocator).getText();
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

    public void selectStartDate(String date) {
        driver.findElement(startDateFieldLocator).click();
        selectDateFromCalendar(date);
    }

    public void selectEndDate(String date) {
        driver.findElement(endDateFieldLocator).click();
        selectDateFromCalendar(date);
    }

    public void selectPromotionType(String type) {
        driver.findElement(typeDropdownLocator).click();
        String xpathType = String.format("//li[@class='mdl-menu__item'][text()='%s']",type);
        driver.findElement(By.xpath(xpathType)).click();
    }

    public void enterValue(int value) {
        driver.findElement(valueFieldLocator).clear();
        driver.findElement(valueFieldLocator).sendKeys(String.valueOf(value));
    }

    public void enterDescription(String description) {
        driver.findElement(descriptionFieldLocator).sendKeys(description);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void addPromotion(Promotion promotion){
        Allure.step(String.format("Enter Name Promotion: %s",promotion.getName()));
        enterNamePromotion(promotion.getName());

        Allure.step(String.format("Enter Code Promotion: %s",promotion.getCode()));
        enterCodePromotion(promotion.getCode());

        Allure.step(String.format("Select Start Date Promotion: %s",promotion.getStartDate()));
        selectStartDate(promotion.getStartDate());

        Allure.step(String.format("Select End Date Promotion: %s",promotion.getEndDate()));
        selectEndDate(promotion.getEndDate());

        Allure.step(String.format("Select Promotion Type: %s",promotion.getType()));
        selectPromotionType(promotion.getType());

        Allure.step(String.format("Enter Value: %d",promotion.getValue()));
        enterValue(promotion.getValue());

        Allure.step(String.format("Enter Description: %s",promotion.getDescription()));
        enterDescription(promotion.getDescription());

        Allure.step("Click Submit Button");
        clickSubmitButton();
    }



}
