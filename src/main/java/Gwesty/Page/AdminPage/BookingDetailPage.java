package Gwesty.Page.AdminPage;

import Gwesty.Model.GuestInRoom;
import Gwesty.Model.Service;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    By roomNumberLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[1]");
    By idNumberLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[2]");
    By fullNameLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[3]");
    By genderLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[4]");
    By dateOfBirthLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[5]");
    By addressLocator = By.xpath("//table[@id='example1']/tbody/tr[@role='row']/td[6]");

    //form add service
    By selectServiceLocator = By.xpath("//select[@name='service']");
    By quantityTextBoxLocator = By.name("qty");
    By submitServiceButtonLocator = By.xpath("//button[@type='submit']");
    By totalServiceLocator = By.xpath("//p[text()='Services']/..//tr/td[6]");
    By totalPaidNightLocator = By.xpath("//b[text()='Total Paid Night']/../following-sibling::td/b");
    By totalPaidServiceLocator = By.xpath("//b[text()='Total Paid service']/../following-sibling::td/b");
    By subTotalAmountLocator = By.xpath("//p[contains(text(),'Sub - Total amount: $')]");
    By discountLocator = By.xpath("//p[contains(text(),'Discount : $')]");
    By taxLocator = By.xpath("//p[contains(text(),'Tax (10%) : $')]");
    By totalBookingLocator = By.xpath("//b[text()='Total :']");
    By totalPaidBookingLocator = By.xpath("//b[text()='Total Paid :']");
    By dueBookingLocator = By.xpath("//b[text()='Due :']");



    public BookingDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Make Confirm Button")
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

    public void selectService(Service service) {
        WebElement dropdownElement = driver.findElement(selectServiceLocator);
        //Cuộn phần tử vào vùng nhìn (viewport) trước khi thực hiện chọn
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectServiceLocator));

        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(String.format("%s($%.1f)", service.getName(), service.getPrice()));
    }

    public void enterQuantityTextBox(int qty){
        driver.findElement(quantityTextBoxLocator).sendKeys(String.valueOf(qty));
    }

    public void clickSubmitServiceButton(){
        driver.findElement(submitServiceButtonLocator).click();
    }


    public void addService(Service service){
        Allure.step("Click Add Service Button");
        clickAddServiceButton();

        Allure.step(String.format("Select Service: %s($%.1f)",service.getName(), service.getPrice()));
        selectService(service);

        Allure.step(String.format("Enter Quantity: %d",service.getQuantity()));
        enterQuantityTextBox(service.getQuantity());

        Allure.step("Click Submit Button");
        clickSubmitServiceButton();
    }

    public String getServiceNameByIndex (int i) {
        String xpathName = String.format("//p[text()='Services']/..//tr[%d]/td[3]", i);
        return driver.findElement(By.xpath(xpathName)).getText();
    }

    public double getServicePriceByIndex(int i) {
        String xpathPrice = String.format("//p[text()='Services']/..//tr[%d]/td[4]", i);
        return Double.parseDouble(driver.findElement(By.xpath(xpathPrice)).getText());
    }

    public int getServiceQuantityByIndex(int i) {
        String xpathPrice = String.format("//p[text()='Services']/..//tr[%d]/td[5]", i);
        return Integer.parseInt(driver.findElement(By.xpath(xpathPrice)).getText());
    }

    public Service getServiceByIndex(int i){
        Service service = new Service();
        service.setName(getServiceNameByIndex(i));
        service.setPrice(getServicePriceByIndex(i));
        service.setQuantity(getServiceQuantityByIndex(i));
        return service;
    }

    public double getServiceTotalByIndex(int i){
        String xpathItemTotal = String.format("//p[text()='Services']/..//tr[%d]/td[6]",i);
        return Double.parseDouble(driver.findElement(By.xpath(xpathItemTotal)).getText().substring(1));
    }

    public double getTotalPaidNight(){
        return Double.parseDouble(driver.findElement(totalPaidNightLocator).getText().substring(1));
    }

    public double getTotalPaidService(){
        return Double.parseDouble(driver.findElement(totalPaidServiceLocator).getText().substring(1));
    }

    public double getSubTotalAmount(){
        return Double.parseDouble(driver.findElement(subTotalAmountLocator).getText().replace("Sub - Total amount: $",""));
    }

    public double getDiscount(){
        return Double.parseDouble(driver.findElement(discountLocator).getText().replace("Discount : $",""));
    }

    public double getTax(){
        return Double.parseDouble(driver.findElement(taxLocator).getText().replace("Tax (10%) : $",""));
    }

    public double getTotalBooking() {
        // Lấy text node liền kề
        String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].nextSibling.textContent;", driver.findElement(totalBookingLocator));

        // Loại bỏ ký tự '$' và chuyển đổi thành số
        return Double.parseDouble(text.replace("$", "").strip());
    }

    public double getTotalPaidBooking(){
        // Lấy text node liền kề
        String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].nextSibling.textContent;", driver.findElement(totalPaidBookingLocator));

        // Loại bỏ ký tự '$' và chuyển đổi thành số
        return Double.parseDouble(text.replace("$", "").strip());
    }

    public double getDueBooking(){
        // Lấy text node liền kề
        String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].nextSibling.textContent;", driver.findElement(dueBookingLocator));

        // Loại bỏ ký tự '$' và chuyển đổi thành số
        return Double.parseDouble(text.replace("$", "").strip());
    }

    //guest in room
    public void clickGuestInRoom() {
        driver.findElement(guestInRoomNavLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addNewButtonLocator));
    }

    public void clickAddNewGuestInRoomButtonLocator() {
        driver.findElement(addNewButtonLocator).click();
    }

    private int getRoomNumber(int i) {
        return Integer.parseInt(driver.findElements(roomNumberLocator).get(i-1).getText());
    }

    private int getIdentifyNumber(int i) {
        return Integer.parseInt(driver.findElements(idNumberLocator).get(i-1).getText());
    }

    private String getFullName(int i) {
        return driver.findElements(fullNameLocator).get(i-1).getText();
    }

    private String getGender(int i) {
        return driver.findElements(genderLocator).get(i-1).getText();
    }

    private String getDateOfBirth(int i)  {
        return driver.findElements(dateOfBirthLocator).get(i-1).getText();
    }

    private String getAddress(int i) {
        return driver.findElements(addressLocator).get(i-1).getText();

    }

    public GuestInRoom getGuestInRoomByIndex(int i) {
        GuestInRoom guest = new GuestInRoom();
        guest.setRoom(getRoomNumber(i));
        guest.setId(getIdentifyNumber(i));
        guest.setFullName(getFullName(i));
        guest.setGender(getGender(i));
        guest.setDateOfBirth(getDateOfBirth(i));
        guest.setAddress(getAddress(i));
        return guest;
    }
}

