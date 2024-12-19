import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.BookingDetailPage;
import Gwesty.Page.AdminPage.BookingPage;
import Gwesty.Page.UserPage.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC13 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    BookingPage bookingPage;
    BookingDetailPage bookingDetailPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    SearchPage searchPage;
    String idBooking;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        bookingPage = new BookingPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        confirmPage = new ConfirmPage(driver);
        roomPage = new RoomPage(driver);
        checkoutPage = new CheckoutPage(driver);
        searchPage = new SearchPage(driver);

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
        //"Pre-condition: Users have been booked a room
        homePage.openLoginPage();
        loginPage.login("thuongnth","123456");
        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1);
        roomDetailPage.bookingRoom("2025/12/18","2025/12/19",1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();
        checkoutPage.paymentByCreditCard("9999 9999 9999 9999","THUONG","10 / 10","999");
        idBooking = confirmPage.getIDBooking();
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        homePage.openHomePage();
        homePage.searchByBookingId(idBooking);
        softAssert.assertEquals(searchPage.getIDBooking(),idBooking,"ID Booking khong trung khop!");
        softAssert.assertEquals(searchPage.getRoomTypeTitle(), confirmPage.getRoomTypeTitle(),"Room type khong trung khop!");
        softAssert.assertEquals(searchPage.getAdult(),String.valueOf(1)+" Adult","Adult Khong trung khop!");
    }
}
