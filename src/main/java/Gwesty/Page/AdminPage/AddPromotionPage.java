package Gwesty.Page.AdminPage;

import Gwesty.Model.Promotion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPromotionPage {

    By nameFieldLocator = By.name("promname");
    By codeFieldLocator = By.name("code");
    By startDateFieldLocator = By.name("startdate");
    By endDateFieldLocator = By.name("enddate");
    By typeDropdownLocator = By.name("type");
    By percentageTypeLocator = By.xpath("//li[@class='mdl-menu__item'][text()='PERCENTAGE']");
    By fixedTypeLocator = By.xpath("//li[@class='mdl-menu__item'][text()='FIXED']");
    By valueFieldLocator = By.name("value");
    By descriptionFieldLocator = By.name("description");
    By calendarPopupLocator = By.xpath("//table[@class='table dtp-picker-days']//tbody"); // Popup chọn ngày
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

    public void selectStartDate(String date) {
        driver.findElement(startDateFieldLocator).click(); // Mở popup lịch
        selectDateFromCalendar(date);
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
        driver.findElement(By.xpath("//td/a[text()='" + targetDay + "']")).click();

        // Nhấn OK để xác nhận
        driver.findElement(oKButtonLocator).click();
    }

    public void selectEndDate(String date) {
        driver.findElement(endDateFieldLocator).click();
        selectDateFromCalendar(date);
    }

    public void selectTypePercentage(String type) {
        driver.findElement(typeDropdownLocator).click();
        String xpathType = String.format("//li[@class='mdl-menu__item'][text()='%s']",type);
        driver.findElement(By.xpath(xpathType)).click();
    }

    public void selectTypeFixed() {
        driver.findElement(typeDropdownLocator).click();
        driver.findElement(fixedTypeLocator).click();
    }

    public void enterValue(int value) {
        driver.findElement(valueFieldLocator).sendKeys(String.valueOf(value));
    }

    public void enterDescription(String description) {
        driver.findElement(descriptionFieldLocator).sendKeys(description);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void addPromotion(Promotion promotion){
        enterNamePromotion(promotion.getName());
        enterCodePromotion(promotion.getCode());
        selectStartDate(promotion.getStartDate());
        selectEndDate(promotion.getEndDate());
        selectTypePercentage(promotion.getType());
        enterValue(promotion.getValue());
        enterDescription(promotion.getDescription());
        clickSubmitButton();
    }



}
