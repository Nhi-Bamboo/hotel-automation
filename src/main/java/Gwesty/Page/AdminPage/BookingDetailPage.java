package Gwesty.Page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingDetailPage {
    WebDriver driver;
    By makeConfirmButtonLocator = By.xpath("//div/a[text()=' Make Confirm']");
    By checkOutButtonLocator = By.xpath("//div/a[text()=' CHECKOUT']");
    By addServiceButtonLocator = By.xpath("//a[normalize-space(text())='Add Service']");
    By roomCheckBoxLocator = By.xpath("//div[@class='custom-control custom-checkbox']/label");
    By submitButtonLocator = By.id("btn-submit");
    By nextButtonLocator = By.id("btn-send");
    By submitButtonOfPaymentFormLocator = By.xpath("//form[@id='payment-form']//button[@type='submit']");
    By payMethodLocator = By.id("payMethod");
    By cardNumberTextboxLocator = By.name("cardNumber");
    By nameTextboxLocator = By.name("ownerName");
    By expiryDateTextboxLocator = By.name("expiry");
    By cvvNumberTextboxLocator = By.name("cvvcode");
    By successButtonLocator = By.xpath("//div/a[text()='SUCCESS']");
    By bookingStatusLocator = By.xpath("//th/b[text()='Booking Status ']/../following-sibling::td/span");

    //guest in room
    By guestInRoomNavLocator = By.xpath("//ul[@id='nav']/li/a[text()='Guest In Room']");
    By addNewButtonLocator = By.xpath("//a[text()=' Add New ']");

    //form add service
    By selectServiceLocator = By.xpath("//select[@name='service']");
    By quantityTextBoxLocator = By.name("qty");
    By submitServiceButtonLocator = By.xpath("//button[@type='submit']");
    By totalServiceLocator = By.xpath("//p[text()='Services']/..//tr/td[6]");
    By totalPaidNightLocator = By.xpath("//b[text()='Total Paid Night']/../following-sibling::td/b");
    By totalPaidServiceLocator = By.xpath("//b[text()='Total Paid service']/../following-sibling::td/b");
    By subTotalAmountLocator = By.xpath("//p[contains(text(),'Sub - Total amount: $')]");


    public BookingDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMakeConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(makeConfirmButtonLocator));
        driver.findElement(makeConfirmButtonLocator).click();
    }

    public boolean isCheckOutButtonDisplayed() {
        return driver.findElement(checkOutButtonLocator).isDisplayed();
    }

    public void clickCheckOutButton() {
        driver.findElement(checkOutButtonLocator).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated((submitButtonLocator)));
    }

    public void checkFirstRoom() {
        driver.findElement(roomCheckBoxLocator).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButtonLocator).click();
    }

    public void selectPaymentMethod(String option) {
       Select dropdown = new Select(driver.findElement(payMethodLocator));
       dropdown.selectByVisibleText(option);
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated((cardNumberTextboxLocator)));
    }

    public void enterCardNumber(String number){
        driver.findElement(cardNumberTextboxLocator).sendKeys(number);
    }

    public void enterName(String name) {
        driver.findElement(nameTextboxLocator).sendKeys(name);
    }

    public void enterExpiryDate(String date) {
        driver.findElement(expiryDateTextboxLocator).sendKeys(date);
    }

    public void enterCVVNumber(int cvv) {
        String n = String.valueOf(cvv);
        driver.findElement(cvvNumberTextboxLocator).sendKeys(n);
    }

    public void enterPaymentInformation(String number, String name, String date, int cvv) {
        enterCardNumber(number);
        enterName(name);
        enterExpiryDate(date);
        enterCVVNumber(cvv);
    }

    public void clickSubMitButtonOfPaymentForm() {
        driver.findElement(submitButtonOfPaymentFormLocator).click();
    }

    public boolean isSuccessButtonDisplayed() {
        return driver.findElement(successButtonLocator).isDisplayed();
    }

    public String getBookingStatus() {
        return driver.findElement(bookingStatusLocator).getText().trim();
    }
    public void clickAddServiceButton(){
        driver.findElement(addServiceButtonLocator).click();
    }

    /*public void selectService(String serviceName) {
        driver.findElement(selectServiceLocator).click();
        List<WebElement> listOptions = driver.findElements(By.tagName("option"));

        for (WebElement option : listOptions) {
            String optionText = option.getText(); // Lấy tên dịch vụ
            if (optionText.equals(serviceName)) {
                option.click(); // Click vào option để chọn dịch vụ
                break;
            }
        }
    }*/
    public void selectService(String option) {
        Select dropdown = new Select(driver.findElement(selectServiceLocator));
        dropdown.selectByVisibleText(option);
    }

    public void enterQuantityTextBox(int qty){
        driver.findElement(quantityTextBoxLocator).sendKeys(String.valueOf(qty));
    }

    public void clickSubmitServiceButton(){
        driver.findElement(submitServiceButtonLocator).click();
    }

    public void addServiceForBooking(String option, int quantity){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectServiceLocator));
        selectService(option);
        enterQuantityTextBox(quantity);
        clickSubmitServiceButton();
    }

    public boolean isServiceNameDisplayed(String serviceName) {
        String xpath = String.format("//p[text()='Services']/..//tr/td[3][text()='%s']", serviceName);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public boolean isServicePriceDisplayed(double price) {
        String xpath = String.format("//p[text()='Services']/..//tr/td[4][text()='%.1f']", price);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public boolean isServiceQuantityDisplayed(int quantity) {
        String xpath = String.format("//p[text()='Services']/..//tr/td[5][text()='%d']", quantity);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public boolean isServiceDisplayed(String serviceName, double price, int quantity){
        if(isServiceNameDisplayed(serviceName) &&
                isServicePriceDisplayed(price) &&
                isServiceQuantityDisplayed(quantity)){
            return true;
        }
        return false;
    }

    public double getServiceTotal(){
        return Double.parseDouble(driver.findElement(totalServiceLocator).getText().substring(1));
    }

    public double getTotalPaidNight(){
        return Double.parseDouble(driver.findElement(totalPaidNightLocator).getText().substring(1));
    }

    public double getTotalPaidService(){
        return Double.parseDouble(driver.findElement(totalPaidServiceLocator).getText().substring(1));
    }

    //-->TC
    public boolean checkSubTotalAmount(){
        double subTotal = Double.parseDouble(driver.findElement(subTotalAmountLocator).getText().replace("Sub - Total amount: $",""));
        if(subTotal == getTotalPaidNight() + getTotalPaidService()){
            return true;
        }
        return false;
    }

    public void clickGuestInRoom() {
        driver.findElement(guestInRoomNavLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addNewButtonLocator));
    }

    public void clickAddNewGuestInRoomButtonLocator() {
        driver.findElement(addNewButtonLocator).click();
    }

}
