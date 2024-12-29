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

public class TC01 {
    WebDriver driver;
    HomePage homePage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    LoginPage loginPage;
    SoftAssert softAssert;
    Random random;
    Faker engFaker;
    int randomYear;
    int randomMonth;
    int randomDay;
    LocalDate startDate;
    LocalDate endDate;
    DateTimeFormatter formatter;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        random = new Random();
        engFaker = new Faker();

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
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {
        homePage.openLoginPage();
        loginPage.login("yennhi","123456");

        homePage.selectRoomPage();
        softAssert.assertTrue(roomPage.isRoomsLabelDisplayed());

        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));
        softAssert.assertTrue(roomDetailPage.isRoomsDetailLabelDisplayed());

        roomDetailPage.bookingRoom(startDate.format(formatter), endDate.format(formatter),1,0);

        softAssert.assertTrue(bookNowPage.isBookNowLabelDisplayed());

        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();

        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225",123);

        softAssert.assertTrue(confirmPage.isPageNameDisplayed());
        softAssert.assertEquals(confirmPage.getMessageSucessfully(),"Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        softAssert.assertAll();

    }
}
