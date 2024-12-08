import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC03 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    SoftAssert softAssert;
    Faker faker;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }

    @Test
    public void test() {
        homePage.openLoginPage();
        loginPage.login("yennhi","123456");
        homePage.clickAccountSetting();
        homePage.openMyAccountPage();

        String imagePath = "C:\\Users\\caonh\\OneDrive\\Pictures\\2.jpg";
        myAccountPage.editAccountInformation(imagePath,
                                                faker.name().fullName(),
                                                faker.internet().emailAddress(),
                                                faker.phoneNumber().cellPhone().replace("-",""),
                                                faker.address().fullAddress());

        softAssert.assertAll();

    }
}

