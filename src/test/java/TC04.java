import Gwesty.Page.AdminPage.AddRoomTypePage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.ViewAllRoomTypePage;
import Gwesty.Page.UserPage.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC04 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddRoomTypePage addRoomTypePage;
    ViewAllRoomTypePage viewAllRoomTypePage;
    SoftAssert softAssert;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        addRoomTypePage = new AddRoomTypePage(driver);
        viewAllRoomTypePage = new ViewAllRoomTypePage(driver);
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
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page View All Room Type
        homePage.openPageAdmin();
        adminPage.clickMenu("Room Types");
        adminPage.clickSubMenu("View\n" +
                "\t\t\t\t\t\t\t\t\t\tAll Room Types");
        viewAllRoomTypePage.searchByTitle("Standard");
        int initialQuantity = viewAllRoomTypePage.quantityOfRoomType("Standard");
        //3. Open Page View All Room Type
        adminPage.clickSubMenu("Add Room Type");
        //3. Enter information
        addRoomTypePage.enterRoomTypeInformation("Standard",500,1,1,"Phong tieu chuan");
        //4. Click button [Submit]
        addRoomTypePage.clickSubmitButton();
        // tìm room type vừa tạo
        viewAllRoomTypePage.searchByTitle("Standard");
        int latestQuantity = viewAllRoomTypePage.quantityOfRoomType("Standard");
        softAssert.assertEquals(latestQuantity-initialQuantity==1,true,"Room type chưa duoc tao.");
        softAssert.assertAll();
    }
}
