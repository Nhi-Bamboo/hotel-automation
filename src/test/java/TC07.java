import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.BookingDetailPage;
import Gwesty.Page.AdminPage.BookingPage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TC07 {
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
    Faker faker;
    String idBooking;

    Date date;
    LocalDate startDate;
    String checkin;
    String checkout;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        bookingPage = new BookingPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        faker = new Faker();

        date =  faker.date().past(3650, java.util.concurrent.TimeUnit.DAYS);
        startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        checkin = String.valueOf(startDate).replace("-","/");
        checkout = String.valueOf(startDate.plusDays(1)).replace("-","/");

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
        Allure.step("Users have been booked and checkin a room");
        homePage.openLoginPage();
        loginPage.login("thuongnth","123456");

        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1);

        roomDetailPage.bookingRoom(checkin,checkout,1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();

        checkoutPage.paymentByCreditCard("9999 9999 9999 9999","THUONG","10 / 10",999);
        idBooking = confirmPage.getIDBooking();
        homePage.Logout();

        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");

        //2. Open Page All Bookings
        homePage.openPageAdmin();
        adminPage.openBookingPage();

        //3. Select the booking with the status 'ONLINE_PENDING'
        bookingPage.searchByID(idBooking);

        //4. Click button [Eye icon]
        bookingPage.openBookingDetail();

        //5. Click button [MAKE CONFIRM]";
        bookingDetailPage.clickMakeConfirmButton();
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {

        //1. Click button [CHECKOUT]
        bookingDetailPage.clickCheckOutButton();


        //2. Select room
        bookingDetailPage.checkFirstRoom();

        //3. Click button [Next]
        bookingDetailPage.clickNextButton();

        //4. Select and input valid values in the Payment Method form
        bookingDetailPage.selectPaymentMethod("CreditCard");

        bookingDetailPage.enterPaymentInformation("9999999999999999","THUONG","10 / 10",999);
        //5. Click button [SUBMIT]bookingDetailPage.clickSubMitButtonOfPaymentForm();
        bookingDetailPage.clickSubMitButtonOfPaymentForm();
        //kiểm tra button success
        softAssert.assertTrue(bookingDetailPage.isSuccessButtonDisplayed(),"Khong hien thi status Success");

        softAssert.assertEquals(bookingDetailPage.getBookingStatus(),"SUCCESS","Booking Status khong dung!");

        softAssert.assertAll();
    }
}
