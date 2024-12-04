import Gwesty.Page.AdminPage.AddRoomTypePage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.ViewAllRoomTypePage;
import Gwesty.Page.UserPage.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC04 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddRoomTypePage addRoomTypePage;
    ViewAllRoomTypePage viewAllRoomTypePage;
    SoftAssert softAssert;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        addRoomTypePage = new AddRoomTypePage(driver);
        viewAllRoomTypePage = new ViewAllRoomTypePage(driver);
        softAssert = new SoftAssert();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page Add Room Type
        homePage.openPageAdmin();
        adminPage.clickMenu("Room Types");
        adminPage.clickSubMenu("Add Room Type");

        //3. Enter information
        addRoomTypePage.enterTitle("Standard");
        addRoomTypePage.enterPrice("500");
        addRoomTypePage.enterAdultCapacity("1");
        addRoomTypePage.enterChildrenCapacity("1");
        addRoomTypePage.enterDescription("Phong tieu chuan");
        //4. Click button [Submit]
        addRoomTypePage.clickSubmitButton();
        viewAllRoomTypePage.enterSearch("Standard");
        softAssert.assertTrue(viewAllRoomTypePage.isRoomTypeDisplayed("Standard"),"Khong tim thay room type vua tao");
        System.out.println(viewAllRoomTypePage.quantityOfRoomType("Standard"));
        softAssert.assertAll();
    }
}
