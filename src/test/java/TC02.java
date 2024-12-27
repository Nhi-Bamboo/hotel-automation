import Gwesty.Page.UserPage.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class TC02 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MyBookingsPage myBookingsPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    SoftAssert softAssert;
    Random random;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myBookingsPage = new MyBookingsPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        softAssert = new SoftAssert();
        random = new Random();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        homePage.openLoginPage();
        loginPage.login("yennhi","123456");
        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));
        roomDetailPage.bookingRoom("2025/01/01","2025/01/02",1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();
        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225",123);
    }

    @AfterMethod
    public void cleanUp() {
       driver.quit();
    }

    @Test
    public void test() {
        String idB = confirmPage.getIDBooking();
        System.out.println(idB);
        homePage.clickAccountSetting();
        homePage.openMyBookingsPage();
        myBookingsPage.clickCancelButtonById(idB);
        myBookingsPage.cancelBooking();
        softAssert.assertFalse(myBookingsPage.isIdBookingDisplayed(idB),"Booking is displayed");
        softAssert.assertAll();

    }
}
