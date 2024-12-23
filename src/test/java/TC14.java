import Gwesty.Page.AdminPage.*;
import Gwesty.Page.UserPage.HomePage;
import Gwesty.Page.UserPage.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC14 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddServicePage addServicePage;
    ViewAllServicesPage viewAllServicesPage;
    Faker faker;

    String serviceName;
    String unit;
    int price;
    String description;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        addServicePage = new AddServicePage(driver);
        viewAllServicesPage = new ViewAllServicesPage(driver);
        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        serviceName = faker.name().firstName();
        unit = faker.name().lastName();
        price = faker.number().numberBetween(100,500);
        description = faker.lorem().sentence();
    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        //Verify that add service successfully when input valid all fields
        //1. Log in with an admin account.
        homePage.openLoginPage();
        loginPage.login("admin","123456");

        //2. Click [Service]
        homePage.openPageAdmin();
        adminPage.openViewAllServices(); //chạy lúc được lúc không
        viewAllServicesPage.searchServiceByName(serviceName);
        int initialQuantity = viewAllServicesPage.countServiceByName(serviceName);

        //3. Click [Add Service]
        viewAllServicesPage.clickAddRowButton();

        //4. Input valid all fields.
        addServicePage.enterServiceInformation(serviceName,unit,price,description);

        //5. Click the [SUBMIT] button.
        addServicePage.clickSubmitButton();

        viewAllServicesPage.searchServiceByName(serviceName);
        int latestQuantity = viewAllServicesPage.countServiceByName(serviceName);

        // verify
        softAssert.assertEquals(latestQuantity-initialQuantity,1,"Tao moi khong thanh cong!");
    }
}
