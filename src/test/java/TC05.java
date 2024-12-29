import Gwesty.Model.Room;
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
    ViewAllRoomTypePage viewAllRoomTypePage;
    SoftAssert softAssert;
    Random random;
    int randomRoomNumber;
    int randomFloor;
    String randomRoomType;
    Room newRoom;
    Room displayedRoom;


    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        addRoomPage = new AddRoomPage(driver);
        searchOnPage = new SearchOnPage(driver);
        viewAllRoomsPage = new ViewAllRoomsPage(driver);
        viewAllRoomTypePage = new ViewAllRoomTypePage(driver);
        softAssert = new SoftAssert();
        random = new Random();
        newRoom = new Room();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        randomRoomNumber = 100000 + random.nextInt(9900000);
        randomFloor = 1 + random.nextInt(10);

        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();

        //get roomType tu ViewRoomTypePage
        adminPage.openAllRoomTypePage();
        randomRoomType = viewAllRoomTypePage.getRoomTypesTitleByIndex(1 + random.nextInt(10));

        // Tạo thông tin room mới
        newRoom.setRoomNo(randomRoomNumber);
        newRoom.setType(randomRoomType);
        newRoom.setFloor(randomFloor);
        newRoom.setStatus(random.nextBoolean());
        newRoom.setDesc("abc123");

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {

        adminPage.openAddRoomPage();

        addRoomPage.addRoomInformation(newRoom);

        // Tìm kiếm
        searchOnPage.searchByNumber(newRoom.getRoomNo());

        // Lấy thông tin phòng sau khi search
        displayedRoom = viewAllRoomsPage.getRoomByIndex(1);

        newRoom.setDesc(null);

        // Kiểm tra thông tin hiển thị có khớp với phòng vừa tạo
        softAssert.assertEquals(displayedRoom, newRoom, "Room does not match!");

        softAssert.assertAll();
    }
}
