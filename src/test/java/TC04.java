import Gwesty.Model.CreditCard;
import Gwesty.Model.RoomType;
import Gwesty.Page.AdminPage.AddRoomTypePage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.ViewAllRoomTypePage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC04 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddRoomTypePage addRoomTypePage;
    ViewAllRoomTypePage viewAllRoomTypePage;
    SoftAssert softAssert;
    RoomType roomType;

    Faker faker;
    String roomTypeTitle;
    double price;
    int adult;
    int children;
    String description;
    int latestQuantity;
    int initialQuantity;

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

        faker = new Faker();
        roomTypeTitle = faker.name().title();
        price = faker.number().numberBetween(100,500);
        adult = 1;
        children = 1;
        description = "Phong tieu chuan";

        roomType = new RoomType();
        roomType.setRoomTypeTitle(roomTypeTitle);
        roomType.setPrice(price);
        roomType.setAdult(adult);
        roomType.setChildren(children);

        roomType.setDescription(description);
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void Test() {
        //1. Login with admin account
        homePage.openLoginPage();
        loginPage.login("admin", "123456");

        //2. Open Page View All Room Type
        homePage.openPageAdmin();
        adminPage.openAllRoomTypePage();
        viewAllRoomTypePage.searchByTitle(roomTypeTitle);
        initialQuantity = viewAllRoomTypePage.countRoomTypesByTitle(roomTypeTitle);

        //3. add new
        viewAllRoomTypePage.clickAddNewButton();

        //4. Enter information
        addRoomTypePage.addRoomTypeInformation(roomType);

        //5. Click button [Submit]
        addRoomTypePage.clickSubmitButton();
        roomType.setDescription(null);

        // tìm room type vừa tạo
        viewAllRoomTypePage.searchByTitle(roomTypeTitle);
        RoomType room = viewAllRoomTypePage.getRoomTypeByIndex(1);
        latestQuantity = viewAllRoomTypePage.countRoomTypesByTitle(roomTypeTitle);

        //assert equal
        softAssert.assertEquals(viewAllRoomTypePage.countRoomTypesByTitle(roomTypeTitle) - initialQuantity,1, "Room type tao khong thanh cong!");
        softAssert.assertEquals(room,roomType,"Thong tin khong trung khop!");
        softAssert.assertAll();
    }
}