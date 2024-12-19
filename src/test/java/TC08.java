import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.BookingDetailPage;
import Gwesty.Page.AdminPage.BookingPage;
import Gwesty.Page.AdminPage.SearchOnPage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Locale;
import java.util.Random;

public class TC08 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    AdminPage adminPage;
    BookingPage bookingPage;
    SearchOnPage searchOnPage;
    BookingDetailPage bookingDetailPage;
    SoftAssert softAssert;
    Faker faker;
    Random random;
    String idB;
    int randomQty;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        adminPage = new AdminPage(driver);
        bookingPage = new BookingPage(driver);
        searchOnPage = new SearchOnPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker(new Locale("vi-VN"));
        random = new Random();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(4);
        roomDetailPage.bookingRoom("2025/01/09","2025/01/10",1,0);
        bookNowPage.addGuestInformation(faker.name().fullName(),faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),faker.address().country());
        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225","123");
        idB = confirmPage.getIDBooking();
        System.out.println(idB);

        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();
        adminPage.openBookingPage();
        searchOnPage.searchByID(idB);
        bookingPage.openBookingDetail();
        bookingDetailPage.clickMakeConfirmButton();
        softAssert.assertEquals(bookingDetailPage.getBookingStatus(),"STAYING","The booking status is incorrect");
    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }

    @Test
    public void test() {
        bookingDetailPage.clickAddServiceButton();
        randomQty = 1 + random.nextInt(10);
        bookingDetailPage.addServiceForBooking("Hoi An Tour($60.0)",randomQty);
        softAssert.assertTrue(bookingDetailPage.isServiceNameDisplayed("Hoi An Tour"),"The service name is incorrect!");
        softAssert.assertTrue(bookingDetailPage.isServicePriceDisplayed(60.0),"The service price is incorrect!");
        softAssert.assertTrue(bookingDetailPage.isServiceQuantityDisplayed(randomQty),"The service quantity is incorrect!");



        softAssert.assertAll();

    }
}
