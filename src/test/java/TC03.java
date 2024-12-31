import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Locale;

public class TC03 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    SoftAssert softAssert;
    Faker faker;
    String name;
    String email;
    String phone;
    String address;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker(new Locale("vi-VN"));

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        name =  faker.name().fullName();
        email = faker.internet().emailAddress();
        phone = faker.phoneNumber().cellPhone().replace(" ","");
        address = faker.address().fullAddress();

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {
        homePage.openLoginPage();
        loginPage.login("yennhi","123456");
        homePage.openMyAccountPage();

        myAccountPage.editAccountInformation(name, email, phone, address);

        //kiểm tra thông tin sau khi nhấn update đã lưu đúng hay chưa?

        softAssert.assertEquals(myAccountPage.getFullNameTextBoxValue(),name,"Update name failed");
        softAssert.assertEquals(myAccountPage.getEmailTextBoxValue(),email,"Update email failed");
        softAssert.assertEquals(myAccountPage.getPhoneTextBoxValue(),phone,"Update phone failed");
        softAssert.assertEquals(myAccountPage.getAddressTextBoxValue(),address,"Update address failed");

        softAssert.assertAll();

    }
}

