import Gwesty.Page.AdminPage.AddRoomTypePage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.ViewAllRoomTypePage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
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
    Faker faker;
    String roomTitle;
    float price;
    int adult;
    int children;
    String description;
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
        faker = new Faker();
        roomTitle = faker.name().title();
        price = 500.0f;
        adult = 1;
        children = 1;
        description = "Phong tieu chuan";
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        //2. Open Page View All Room Type
        homePage.openPageAdmin();
        adminPage.openAllRoomTypePage();
        viewAllRoomTypePage.searchByTitle(roomTitle);
        int initialQuantity = viewAllRoomTypePage.quantityOfRoomType(roomTitle);
        //3. add new
        viewAllRoomTypePage.clickAddNewButton();
        //4. Enter information
        addRoomTypePage.enterRoomTypeInformation(roomTitle,price,adult,children,description);
        //5. Click button [Submit]
        addRoomTypePage.clickSubmitButton();
        // tìm room type vừa tạo
        viewAllRoomTypePage.searchByTitle(roomTitle);
        int latestQuantity = viewAllRoomTypePage.quantityOfRoomType(roomTitle);
        softAssert.assertTrue(latestQuantity-initialQuantity==1,"Room type tao khong thanh cong!");
        softAssert.assertEquals(viewAllRoomTypePage.getRoomTypesTitle(),roomTitle,"Title Room Types khong trung khop!");
        softAssert.assertEquals(viewAllRoomTypePage.getAdultCapacity(),adult,"Adult Capacity khong trung khop!");
        softAssert.assertEquals(viewAllRoomTypePage.getChildrenCapacity(),children,"Children Capacity khong trung khop!");
        softAssert.assertEquals(viewAllRoomTypePage.getPrice(),price,"Price khong trung khop!");
        softAssert.assertAll();
    }
}
