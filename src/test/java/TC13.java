import Gwesty.Model.Booking;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.BookingDetailPage;
import Gwesty.Page.AdminPage.BookingPage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
    Faker faker;

    String idBooking;
    Date date;
    LocalDate startDate;
    String checkin;
    String checkout;
    Booking confirm;
    Booking search;

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
        faker = new Faker();

        date =  faker.date().past(3650, java.util.concurrent.TimeUnit.DAYS);
        startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        checkin = String.valueOf(startDate).replace("-","/");
        checkout = String.valueOf(startDate.plusDays(1)).replace("-","/");

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        //"Pre-condition: Users have been booked a room
        homePage.openLoginPage();
        loginPage.login("thuongnth","123456");

        homePage.selectRoomPage();

        roomPage.openDetailRoomByIndex(1);

        roomDetailPage.bookingRoom(checkin,checkout,1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();

        checkoutPage.paymentByCreditCard("9999 9999 9999 9999","THUONG","10 / 10",999);
        idBooking = confirmPage.getIDBooking();

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        confirm = confirmPage.getConfirmBookingInformation();
        homePage.openHomePage();
        homePage.searchByBookingId(idBooking);
        search = searchPage.getSearchBookingInformation();

        softAssert.assertEquals(search,confirm,"Thong tin khong trung khop!");
        softAssert.assertAll();
    }
}
