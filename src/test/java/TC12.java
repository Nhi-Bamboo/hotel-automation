import Gwesty.Model.CreditCard;
import Gwesty.Page.AdminPage.AddCreditCardPage;
import Gwesty.Page.AdminPage.AdminPage;
import Gwesty.Page.AdminPage.ViewAllCreditCardPage;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
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
    SoftAssert softAssert;
    Random random;
    CreditCard creditCard;
    List<String> tabs;
    Faker vnFaker;
    Faker engFaker;
    int randomCard;
    double totalCharge;

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
        softAssert = new SoftAssert();
        random = new Random();
        creditCard = new CreditCard();
        vnFaker = new Faker(new Locale("vi-VN"));
        engFaker = new Faker();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();
        adminPage.openViewAllCreditCard();

        randomCard = 1 + random.nextInt(10);
        viewAllCreditCardPage.openDetailCreditCardByIndex(randomCard);

        creditCard.setNumber(addCreditCardPage.getCreditCardNumber());
        creditCard.setName(addCreditCardPage.getOwnerName());
        creditCard.setMonth(addCreditCardPage.getExpiryMonth());
        creditCard.setYear(addCreditCardPage.getExpiryYear());
        creditCard.setCvv(addCreditCardPage.getCvvCode());
        creditCard.setBalance(addCreditCardPage.getBalance());

        // 2. Mở tab mới: Trang user
        ((JavascriptExecutor) driver).executeScript("window.open('http://14.176.232.213:8084/', '_blank');");

        // 3. Lấy danh sách các tab
        tabs = new ArrayList<>(driver.getWindowHandles());

        // Chuyển sang tab User (Tab thứ 2)
        driver.switchTo().window(tabs.get(1));
    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }

    @Test
    public void test() {

        homePage.selectRoomPage();

        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));

        roomDetailPage.bookingRoom("2025/01/25","2025/01/26",1,0);

        bookNowPage.addGuestInformation(vnFaker.name().fullName(),
                engFaker.internet().emailAddress(),
                vnFaker.phoneNumber().cellPhone().replace(" ",""),
                vnFaker.address().country());

        checkoutPage.enterCreditCardInformation(creditCard);

        softAssert.assertTrue(confirmPage.isPageNameDisplayed());
        softAssert.assertTrue(confirmPage.isMessageDisplayed());

        //get total charge
        totalCharge = confirmPage.getTotalCharge();

        //close tab user
        driver.close();

        driver.switchTo().window(tabs.get(0));

        //Open All Credit Card Tab
        addCreditCardPage.openAllCreditCardTab();

        softAssert.assertEquals(viewAllCreditCardPage.getBalance(randomCard),creditCard.getBalance() - totalCharge,"Balance is correct!!!");

        softAssert.assertAll();

    }
}
