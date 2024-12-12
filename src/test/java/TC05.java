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
    AllRoomsPage allRoomsPage;
    SoftAssert softAssert;


    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        addRoomPage = new AddRoomPage(driver);
        allRoomsPage = new AllRoomsPage(driver);
        softAssert = new SoftAssert();

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
        adminPage.openAllRoomTypePage();

        //random 6 - 7 chữ số

        allRoomsPage.searchByRoomNumber(roomNo);
        //int initial = allRoomsPage.quantityOfRoom(roomNo);

        adminPage.clickSubMenu("Add Room");
        addRoomPage.addRoomInformation(roomNo,"Junior Suite",5,"abc123");

        allRoomsPage.searchByRoomNumber(roomNo);
        //int latest = allRoomsPage.quantityOfRoom(roomNo);

        softAssert.assertEquals(latest, initial + 1,"Failed");
        softAssert.assertAll();
    }
}
