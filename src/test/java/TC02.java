import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    int randomYear;
    int randomMonth;
    int randomDay;
    LocalDate startDate;
    LocalDate endDate;
    DateTimeFormatter formatter;
    Faker engFaker;
    String idB;

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

        //Tạo năm ngẫu nhiên từ 2024 đến 2030
        randomYear = engFaker.number().numberBetween(2025, 2031);  // 2027 không bao gồm

        //Tạo ngày ngẫu nhiên trong năm
        randomMonth = engFaker.number().numberBetween(1, 13);
        randomDay = engFaker.number().numberBetween(1, 32);

        //Tạo ngày bắt đầu ngẫu nhiên
        startDate = LocalDate.of(randomYear, randomMonth, randomDay);

        //Ngày kết thúc = ngày bắt đầu + 1
        endDate = startDate.plusDays(1);

        // Định dạng ngày theo format "yyyy/MM/dd"
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        homePage.openLoginPage();
        loginPage.login("yennhi","123456");
        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));
        roomDetailPage.bookingRoom("2025/01/01","2025/01/02",1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();
        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225",123);
        idB = confirmPage.getIDBooking();
    }

    @AfterMethod
    public void cleanUp() {
       driver.quit();
    }

    @Test
    public void test() {
        homePage.openMyBookingsPage();
        myBookingsPage.cancelBooking(idB);
        softAssert.assertFalse(myBookingsPage.isIdBookingDisplayed(idB),"Booking is displayed");
        softAssert.assertAll();

    }
}
