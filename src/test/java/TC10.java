import Gwesty.Model.Promotion;
import Gwesty.Page.AdminPage.AddPromotionPage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TC10 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    AdminPage adminPage;
    AddPromotionPage addPromotionPage;
    String originalHandle;
    SoftAssert softAssert;
    Faker engFaker;
    Random random;
    int startYear;
    LocalDate randomStartDatePromotion;
    LocalDate randomEndDatePromotion;
    DateTimeFormatter formatterPromotionDate;
    int randomYear;
    int randomMonth;
    int randomDay;
    LocalDate startDateCheckIn;
    LocalDate endDateCheckOut;
    DateTimeFormatter formatterSearchRoom;
    LocalDate currentDate;
    int randomDaysBeforeNow;
    int randomDaysAfterNow;

    Promotion promotionAdded;
    int daysUntilNow;


    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        adminPage = new AdminPage(driver);
        addPromotionPage = new AddPromotionPage(driver);
        softAssert = new SoftAssert();
        engFaker = new Faker();
        random = new Random();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        //1. random promotion date
        startYear = 2024;
        // Lấy ngày hiện tại
        currentDate = LocalDate.now();
        // Random ngày bắt đầu (startDate < currentDate)
        daysUntilNow = (int) currentDate.toEpochDay() - (int) LocalDate.of(startYear, 1, 1).toEpochDay();
        randomDaysBeforeNow = random.nextInt(daysUntilNow) + 1; // Random trong khoảng từ ngày bắt đầu đến hiện tại
        randomStartDatePromotion = currentDate.minusDays(randomDaysBeforeNow);
        // Random ngày kết thúc sau ngày bắt đầu (endDate > startDate)
        randomDaysAfterNow = random.nextInt(30) + 1; // Random số ngày từ ngày hiện tại
        randomEndDatePromotion = currentDate.plusDays(randomDaysAfterNow);
        // Format ngày theo yyyy-MM-dd
        formatterPromotionDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        //2. random ngày -  search Room
        randomYear = engFaker.number().numberBetween(2025, 2031);  // 2027 không bao gồm
        //Tạo ngày ngẫu nhiên trong năm
        randomMonth = engFaker.number().numberBetween(1, 13);
        randomDay = engFaker.number().numberBetween(1, 32);
        //Tạo ngày bắt đầu ngẫu nhiên
        startDateCheckIn = LocalDate.of(randomYear, randomMonth, randomDay);
        //Ngày kết thúc = ngày bắt đầu + 1
        endDateCheckOut = startDateCheckIn.plusDays(1);
        // Định dạng ngày theo format "yyyy/MM/dd"
        formatterSearchRoom = DateTimeFormatter.ofPattern("yyyy/MM/dd");


        promotionAdded = new Promotion();
        promotionAdded.setName(engFaker.company().buzzword() + " Sale!");
        promotionAdded.setCode(engFaker.bothify("PROMO-FIXED-###??"));  //#: Tạo số ngẫu nhiên (0-9).    ?: Tạo chữ cái ngẫu nhiên (A-Z).
        promotionAdded.setStartDate(randomStartDatePromotion.format(formatterPromotionDate));
        promotionAdded.setEndDate(randomEndDatePromotion.format(formatterPromotionDate));
        promotionAdded.setType("FIXED");
        promotionAdded.setValue(1 + random.nextInt(101));
        promotionAdded.setDescription("Booking Promotion");
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {
        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();

        //Mở page Add promotion
        adminPage.openAddPromotionPage();
        addPromotionPage.addPromotion(promotionAdded);

        // Lưu lại handle của cửa sổ/tab hiện tại
        originalHandle = driver.getWindowHandle();

        // Mở một tab mới
        driver.switchTo().newWindow(WindowType.TAB);

        // Truy cập trang user
        driver.get("http://14.176.232.213:8084/");

        homePage.selectRoomPage();

        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));

        roomDetailPage.bookingRoom(startDateCheckIn.format(formatterSearchRoom), endDateCheckOut.format(formatterSearchRoom), 1,0);
        bookNowPage.addPromotionCode(promotionAdded.getCode());

        softAssert.assertEquals((int) bookNowPage.getDiscount(), promotionAdded.getValue(), "The discount value is incorrect!");

        softAssert.assertEquals(bookNowPage.getGrandTotal(),
                bookNowPage.getSubTotal() + bookNowPage.getTax() - bookNowPage.getDiscount(),
                "The Grand Total is incorrect!");

        driver.close();

        softAssert.assertAll();

    }
}

