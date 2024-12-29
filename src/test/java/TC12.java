import Gwesty.Model.CreditCard;
import Gwesty.Page.AdminPage.AddCreditCardPage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.SearchOnPage;
import Gwesty.Page.AdminPage.ViewAllCreditCardPage;
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
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TC12 {
    WebDriver driver;
    HomePage homePage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    LoginPage loginPage;
    AdminPage adminPage;
    ViewAllCreditCardPage viewAllCreditCardPage;
    AddCreditCardPage addCreditCardPage;
    SearchOnPage searchOnPage;
    SoftAssert softAssert;
    Random random;
    CreditCard creditCard;
    List<String> tabs;
    Faker vnFaker;
    Faker engFaker;
    String creditCardNumber;
    String ownerName;
    double totalCharge;
    int month;
    int year;
    int cvv;
    double balance;
    String originalHandle;
    int randomYear;
    int randomMonth;
    int randomDay;
    LocalDate startDate;
    LocalDate endDate;
    DateTimeFormatter formatter;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        viewAllCreditCardPage = new ViewAllCreditCardPage(driver);
        addCreditCardPage = new AddCreditCardPage(driver);
        searchOnPage = new SearchOnPage(driver);
        softAssert = new SoftAssert();
        random = new Random();
        vnFaker = new Faker(new Locale("vi-VN"));
        engFaker = new Faker();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        //Tạo năm ngẫu nhiên từ 2024 đến 2030
        randomYear = engFaker.number().numberBetween(2025, 2031);  // 2027 không bao gồm

        //Tạo ngày ngẫu nhiên trong năm
        randomMonth = engFaker.number().numberBetween(1, 13);
        randomDay = engFaker.number().numberBetween(1, 32);

        //Tạo ngày bắt đầu ngẫu nhiên
        startDate = LocalDate.of(randomYear, randomMonth, randomDay);

        //Ngày kết thúc = ngày bắt đầu + 1
        endDate = startDate.plusDays(1);

        // Định dạng ngày theo format "yyyy/MM/dd"
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();
        adminPage.openAddCreditCard();

        //Thêm CreditCard
        creditCardNumber = engFaker.bothify("#### #### #### ####"); //   #: Tạo số ngẫu nhiên (0-9).
        ownerName = engFaker.name().firstName().toUpperCase();
        month = engFaker.number().numberBetween(1, 13);
        year = engFaker.number().numberBetween(1, 100);
        cvv = engFaker.number().numberBetween(100,1000);
        balance = engFaker.number().numberBetween(10000,100000);

        creditCard = new CreditCard();
        creditCard.setNumber(creditCardNumber);
        creditCard.setName(ownerName);
        creditCard.setMonth(month);
        creditCard.setYear(year);
        creditCard.setCvv(cvv);
        creditCard.setBalance(balance);

        addCreditCardPage.addCreditCardInformation(creditCard);

        // Lưu lại handle của cửa sổ/tab hiện tại
        originalHandle = driver.getWindowHandle();

        // Mở một tab mới
        driver.switchTo().newWindow(WindowType.TAB);

        // Truy cập trang user
        driver.get("http://14.176.232.213:8084/");

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {

        homePage.selectRoomPage();

        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));

        roomDetailPage.bookingRoom(startDate.format(formatter), endDate.format(formatter), 1,0);

        System.out.println(startDate.format(formatter));
        System.out.println(endDate.format(formatter));

        bookNowPage.addGuestInformation(vnFaker.name().fullName(),
                engFaker.internet().emailAddress(),
                vnFaker.phoneNumber().cellPhone().replace(" ",""),
                vnFaker.address().country());

        checkoutPage.enterCreditCardInformation(creditCard);

        softAssert.assertTrue(confirmPage.isPageNameDisplayed());
        softAssert.assertEquals(confirmPage.getMessageSucessfully(),"Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        //get total charge
        totalCharge = confirmPage.getTotalCharge();

        //close tab user
        driver.close();

        driver.switchTo().window(originalHandle);

        //Search card
        viewAllCreditCardPage.openAllCreditCardTab();
        searchOnPage.searchByString(creditCardNumber + " " + ownerName);

        System.out.println(viewAllCreditCardPage.getBalance(1));

        softAssert.assertEquals(viewAllCreditCardPage.getBalance(1),balance - totalCharge,"Balance is incorrect!!!");

        softAssert.assertAll();

    }
}
