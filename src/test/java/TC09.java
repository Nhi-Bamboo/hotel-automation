import Gwesty.Model.Promotion;
import Gwesty.Page.AdminPage.*;
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
import java.util.Locale;
import java.util.Random;

public class TC09 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    AdminPage adminPage;
    SearchOnPage searchOnPage;
    AddPromotionPage addPromotionPage;
    SoftAssert softAssert;
    Faker vnFaker;
    Faker engFaker;
    Random random;

    Promotion promotionAdded;


    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        adminPage = new AdminPage(driver);
        searchOnPage = new SearchOnPage(driver);
        addPromotionPage = new AddPromotionPage(driver);
        softAssert = new SoftAssert();
        vnFaker = new Faker(new Locale("vi-VN"));
        engFaker = new Faker();
        random = new Random();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        int startYear = 2024;
        int endYear = 2030;

        // Random ngày bắt đầu
        int randomStartYear = startYear + random.nextInt(endYear - startYear + 1);
        int randomStartDayOfYear = random.nextInt(LocalDate.of(randomStartYear, 12, 31).getDayOfYear()) + 1;
        LocalDate randomStartDate = LocalDate.ofYearDay(randomStartYear, randomStartDayOfYear);

        // Random ngày kết thúc sau ngày bắt đầu
        int randomDaysToAdd = random.nextInt(30) + 1;
        LocalDate randomEndDate = randomStartDate.plusDays(randomDaysToAdd);

        // Format ngày theo yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        promotionAdded = new Promotion();
        promotionAdded.setName(engFaker.company().buzzword() + " Sale!");
        promotionAdded.setCode(engFaker.bothify("PROMO-###??")); //   #: Tạo số ngẫu nhiên (0-9).    ?: Tạo chữ cái ngẫu nhiên (A-Z).
        promotionAdded.setStartDate(randomStartDate.format(formatter));
        promotionAdded.setEndDate(randomEndDate.format(formatter));
        promotionAdded.setType("PERCENTAGE");
        promotionAdded.setValue(1 + random.nextInt(20));
        promotionAdded.setDescription("Booking Promotion");
        System.out.println(randomStartDate.format(formatter));
        System.out.println(randomEndDate.format(formatter));
    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }

    @Test
    public void test() {
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();

        //Mở page Add promotion
        adminPage.openAddPromotionPage();
        addPromotionPage.addPromotion(promotionAdded);

        softAssert.assertAll();

    }
}
