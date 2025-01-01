package Gwesty.Page.UserPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v127.network.model.AlternateProtocolUsage;

public class BookNowPage {
    By nameTextBoxLocator = By.id("name");
    By emailTextBoxLocator = By.id("email");
    By phoneTextBoxLocator = By.id("phone");
    By addressTextBoxLocator = By.id("address");
    By checkBoxAgreeLocator = By.xpath("//*[@class='custom-control custom-checkbox']");
    By submitButtonLocator = By.xpath("//input[@value='Submit']");
    By bookNowLabelLocator = By.xpath("//h2[text()='Book Now']");
    By radioIHavePromotionLocator = By.xpath("//label[@class='custom-control custom-radio m-0']");
    By promotionCodeTextBoxLocator = By.name("promoCode");
    By applyButtonLocator = By.xpath("//button[text()='Apply']");
    By subTotalLocator = By.xpath("//tr[2]//strong[contains(text(),'$')]");
    By taxLocator = By.xpath("//tr[3]//td[contains(text(),'$')]");
    By discountLocator = By.xpath("//tr[4]//td[contains(text(),'$')]");
    By grandTotalLocator = By.xpath("//tr[5]//strong[contains(text(),'$')]");

    public BookNowPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterNameTextBox(String name){
        driver.findElement(nameTextBoxLocator).clear();
        driver.findElement(nameTextBoxLocator).sendKeys(name);
    }

    public void enterEmailTextBox(String email){
        driver.findElement(emailTextBoxLocator).clear();
        driver.findElement(emailTextBoxLocator).sendKeys(email);
    }

    public void enterPhoneTextBox(String phone){
        driver.findElement(phoneTextBoxLocator).clear();
        driver.findElement(phoneTextBoxLocator).sendKeys(phone);
    }

    public void enterAddressTextBox(String address){
        driver.findElement(addressTextBoxLocator).clear();
        driver.findElement(addressTextBoxLocator).sendKeys(address);
    }

    public void checkCheckBoxAgree(){
        Allure.step("Click CheckBox 'I agree with Terms and Conditions'");
        if (!driver.findElement(checkBoxAgreeLocator).isSelected()){
            driver.findElement(checkBoxAgreeLocator).click();
        }
    }

    public void clickSubmitButton(){
        Allure.step("Click Submit Button");
        driver.findElement(submitButtonLocator).click();
    }

    @Step("Add Booker Information")
    public void addBookerInformation(String name, String e, String phone, String address){
        Allure.step(String.format("Enter Name: %s",name));
        enterNameTextBox(name);

        Allure.step(String.format("Enter Email: %s",e));
        enterEmailTextBox(e);

        Allure.step(String.format("Enter Phone: %s",phone));
        enterPhoneTextBox(phone);

        Allure.step(String.format("Enter Address: %s",address));
        enterAddressTextBox(address);

        Allure.step("Click 'I agree with Terms and Conditions'");
        checkCheckBoxAgree();

        Allure.step("Click Submit Button");
        clickSubmitButton();
    }

    public boolean isBookNowLabelDisplayed(){
        return driver.findElement(bookNowLabelLocator).isDisplayed();
    }

    public void enterBookingNowInformation(String name, String email, String phone, String address) {
        enterNameTextBox(name);
        enterEmailTextBox(email);
        enterPhoneTextBox(phone);
        enterAddressTextBox(address);

    }

    public void clickRadioIHavePromotion(){
        driver.findElement(radioIHavePromotionLocator).click();
    }

    public void enterPromotionCode(String code){
        driver.findElement(promotionCodeTextBoxLocator).sendKeys(code);
    }

    public void clickApplyButton(){
        driver.findElement(applyButtonLocator).click();
    }

    public void addPromotionCode(String promoCode){
        Allure.step("Click Radio 'I Have Promotion'");
        clickRadioIHavePromotion();

        Allure.step(String.format("Enter Promotion Code: %s",promoCode));
        enterPromotionCode(promoCode);

        Allure.step("Click Apply Button");
        clickApplyButton();
    }

    public double getSubTotal(){
        return Double.parseDouble(driver.findElement(subTotalLocator).getText().substring(1));
    }

    public double getTax(){
        return Double.parseDouble(driver.findElement(taxLocator).getText().substring(1));
    }

    public double getDiscount(){
        return Double.parseDouble(driver.findElement(discountLocator).getText().substring(1));
    }

    public double getGrandTotal(){
        return Double.parseDouble(driver.findElement(grandTotalLocator).getText().substring(1));
    }
}
