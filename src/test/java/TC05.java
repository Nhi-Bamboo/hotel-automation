import Gwesty.Page.AdminPage.*;
import Gwesty.Page.UserPage.HomePage;
import Gwesty.Page.UserPage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class TC05 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddRoomPage addRoomPage;
    SearchOnPage searchOnPage;
    ViewAllRoomsPage viewAllRoomsPage;
    SoftAssert softAssert;
    Random random;
    int randomRoomNumber;
    int randomFloor;


    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        addRoomPage = new AddRoomPage(driver);
        searchOnPage = new SearchOnPage(driver);
        viewAllRoomsPage = new ViewAllRoomsPage(driver);
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
    public void Test() {
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();
        adminPage.openAddRoomPage();

        //random 6 - 7 chữ số
        randomRoomNumber = 100000 + random.nextInt(9900000);
        randomFloor = 1 + random.nextInt(10);

        //Add room
        addRoomPage.addRoomInformation(randomRoomNumber,"Junior Suite",randomFloor,"abc123");

        //Search room
        searchOnPage.searchByRoomNumber(randomRoomNumber);

        //Kiểm tra room đã tạo có hiển thị đúng thông tin hay không?
        softAssert.assertTrue(viewAllRoomsPage.isRoomDisplayed(randomRoomNumber,"Junior Suite",randomFloor,1),"failed");

        softAssert.assertAll();
    }
}
