import Gwesty.Page.UserPage.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
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

        roomDetailPage.bookingRoom("2025/01/25","2025/01/26",1,0);

        softAssert.assertTrue(bookNowPage.isBookNowLabelDisplayed());

        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();

        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225",123);

        softAssert.assertTrue(confirmPage.isPageNameDisplayed());
        softAssert.assertTrue(confirmPage.isMessageDisplayed());

        softAssert.assertAll();

    }
}
