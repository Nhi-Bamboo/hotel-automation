import Gwesty.Page.HomePage;
import Gwesty.Page.RoomPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC01{
    WebDriver driver;
    HomePage homePage;
    RoomPage roomPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        roomPage = new RoomPage(driver);
        softAssert = new SoftAssert();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");
    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }

    @Test
    public void test() {
        homePage.searchRooms("2024/11/25","2024/11/26",1,0);
        softAssert.assertTrue(roomPage.isRoomsDisplayed());
        softAssert.assertAll();

    }
}
