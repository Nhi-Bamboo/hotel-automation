import Gwesty.Page.AdminPage.AdminPage;
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
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    SearchRoomsPage searchRoomsPage;
    SoftAssert softAssert;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        searchRoomsPage = new SearchRoomsPage(driver);
        softAssert = new SoftAssert();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page Add Room Type
        homePage.openPageAdmin();
        adminPage.OpenAddRoomTypesPage();
        //3. Enter information

        //4. Click button [Submit]
    }
}
