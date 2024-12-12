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

public class TC07 {
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
        //Pre-condition: Users have been booked a room
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page All Bookings
        homePage.openPageAdmin();
        adminPage.clickMenu("Booking");
        //3. Select the booking with the status 'STAYING'
        bookingPage.searchByID("5253-857700041");
        //4. Click button [Eye icon]
        bookingPage.openBookingDetail();
        //5. Click button [CHECKOUT]
        softAssert.assertTrue(bookingDetailPage.isCheckOutButtonDisplayed(),"Khong hien thi Checkout button");
        bookingDetailPage.clickCheckOutButton();
        //6. Select room
        bookingDetailPage.clickRoomCheckBox();
        //7. Click button [Next]
        bookingDetailPage.clickNextButton();
        //8. Select and input valid values in the Payment Method form
        bookingDetailPage.selectPaymentMethod("CreditCard");
        bookingDetailPage.enterPaymentInformation("9999999999999999","THUONG","10/10",999);
        //9. Click button [SUBMIT]
        bookingDetailPage.clickSubMitButtonOfPaymentForm();
        //kiểm tra button success
        softAssert.assertTrue(bookingDetailPage.isSuccessButtonDisplayed(),"Khong hien thi status Success");
        softAssert.assertAll();
    }
}
