import Gwesty.Page.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC02 {
    WebDriver driver;
    HomePage homePage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    SearchRoomsPage searchRoomsPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        searchRoomsPage = new SearchRoomsPage(driver);
        softAssert = new SoftAssert();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {
        homePage.selectRoomPage();
        softAssert.assertTrue(roomPage.isRoomsLabelDisplayed());

        roomPage.openDetailRoomByIndex(2);
        softAssert.assertTrue(roomDetailPage.isRoomsDetailLabelDisplayed());

        roomDetailPage.bookingRoom("2025/12/15","2025/12/16",1,0);

        //softAssert.assertTrue(bookNowPage.isBookNowLabelDisplayed());

        bookNowPage.enterNameTextBox("Yến Nhi");
        bookNowPage.enterEmailTextBox("cus@gmail.com");
        bookNowPage.enterPhoneTextBox("0912345678");
        bookNowPage.enterAddressTextBox("Vietnam");
        bookNowPage.clickCheckBoxAgree();
        bookNowPage.openSubmitButton();

        checkoutPage.enterCardNumberBox("2222333344445555");
        checkoutPage.enterNameOnCardBox("JOHN HENRY");
        checkoutPage.enterExpiryDateBox("1225");
        checkoutPage.enterCvvNumberBox("123");
        checkoutPage.openPayNowButton();
        //softAssert.assertTrue(checkoutPage.isMessageDisplayed());

        checkoutPage.selectRoomsPage();
        roomPage.openDetailRoomByIndex(2);

        roomDetailPage.bookingRoom("2025/12/15","2025/12/16",1,0);

        softAssert.assertTrue(searchRoomsPage.isMessageDisplayed());

        softAssert.assertAll();

    }
}
