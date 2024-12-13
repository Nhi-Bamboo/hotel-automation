import Gwesty.Page.AdminPage.*;
import Gwesty.Page.UserPage.HomePage;
import Gwesty.Page.UserPage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC11 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddCreditCardPage addCreditCardPage;
    ViewAllCreditCardPage viewAllCreditCardPage;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        addCreditCardPage = new AddCreditCardPage(driver);
        viewAllCreditCardPage = new ViewAllCreditCardPage(driver);
        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //1. Log in with an admin account.
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Click [CreditCard]
        homePage.openPageAdmin();
//        adminPage.clickMenu("CreditCard");
        //3. Click [Add CreditCard]
//        adminPage.clickSubMenu("Add CreditCard");
        //4. Input valid all fields.
        addCreditCardPage.enterCreditCardInformation("7777777777778888","THUONG",11,2025,999,900000);
        //5. Click the [SUBMIT] button.
        addCreditCardPage.clickSubmitButton();
        //verify
        viewAllCreditCardPage.searchByCreditCardNumber("7777");
        softAssert.assertEquals(viewAllCreditCardPage.isCreditCardNumberDisplayed("7777 7777 7777 8888"),true,"Khong tao moi thanh cong");
        softAssert.assertAll();
    }
}
