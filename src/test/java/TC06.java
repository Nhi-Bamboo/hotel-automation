import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.BookingDetailPage;
import Gwesty.Page.AdminPage.BookingPage;
import Gwesty.Page.UserPage.HomePage;
import Gwesty.Page.UserPage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC06 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    BookingPage bookingPage;
    BookingDetailPage bookingDetailPage;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        bookingPage = new BookingPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //"Pre-condition: Users have been booked a room
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page All Bookings
        homePage.openPageAdmin();
        adminPage.clickMenu("Booking");
        //3. Select the booking with the status 'ONLINE_PENDING'
        bookingPage.searchByID("7254-299900033");
        //4. Click button [Eye icon]
        bookingPage.openBookingDetail();

        //5. Click button [MAKE CONFIRM]";
        bookingDetailPage.clickMakeConfirmButton();
        softAssert.assertEquals(bookingDetailPage.isCheckOutButtonDisplayed(),true,"Khong hien thi button check out!");
        softAssert.assertAll();
    }
}