import Gwesty.Model.Service;
import Gwesty.Page.AdminPage.*;
import Gwesty.Page.UserPage.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Locale;
import java.util.Random;

public class TC08 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    AdminPage adminPage;
    BookingPage bookingPage;
    SearchOnPage searchOnPage;
    BookingDetailPage bookingDetailPage;
    ViewAllServicesPage viewAllServicesPage;
    SoftAssert softAssert;
    Faker vnFaker;
    Faker engFaker;
    Random random;
    String idB;
    int randomQty;
    int randomService;
    String serviceName;
    double servicePrice;
    Service serviceAdded;
    Service displayedService;

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
        bookingPage = new BookingPage(driver);
        searchOnPage = new SearchOnPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        viewAllServicesPage = new ViewAllServicesPage(driver);
        softAssert = new SoftAssert();
        vnFaker = new Faker(new Locale("vi-VN"));
        engFaker = new Faker();
        random = new Random();

        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1 + random.nextInt(10));
        roomDetailPage.bookingRoom("2025/01/09","2025/01/10",1,0);
        bookNowPage.addGuestInformation(vnFaker.name().fullName(),
                                        engFaker.internet().emailAddress(),
                                        vnFaker.phoneNumber().cellPhone().replace(" ",""),
                                        vnFaker.address().country());
        checkoutPage.paymentByCreditCard("2222333344445555","JOHN HENRY","1225",123);
        idB = confirmPage.getIDBooking();
        System.out.println(idB);

        homePage.openLoginPage();
        loginPage.login("admin","123456");
        homePage.openPageAdmin();

        //get service name + price
        adminPage.openViewAllServices();
        randomService = 1 + random.nextInt(10);
        serviceName = viewAllServicesPage.getServiceName(randomService);
        servicePrice = viewAllServicesPage.getPrice(randomService);

        //gán set
        randomQty = 1 + random.nextInt(10);
        serviceAdded = new Service();
        serviceAdded.setName(serviceName);
        serviceAdded.setPrice(servicePrice);
        serviceAdded.setQuantity(randomQty);

        //make confirm booking
        adminPage.openBookingPage();
        searchOnPage.searchByString(idB);
        bookingPage.openBookingDetail();
        bookingDetailPage.clickMakeConfirmButton();

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void test() {
        bookingDetailPage.clickAddServiceButton();

        bookingDetailPage.addService(serviceAdded);

        displayedService = bookingDetailPage.getServiceByIndex(1);

        softAssert.assertEquals(displayedService,serviceAdded,"Service does not match!!!");

        softAssert.assertEquals(bookingDetailPage.getServiceTotalByIndex(1),
                        displayedService.getPrice() * displayedService.getQuantity(),
                        "Service Total is incorrect!!");

        softAssert.assertEquals(bookingDetailPage.getSubTotalAmount(),
                        bookingDetailPage.getTotalPaidNight() + bookingDetailPage.getTotalPaidService(),
                        "Sub - Total amount is incorrect!!");

        softAssert.assertEquals(bookingDetailPage.getTotalBooking(),
                        bookingDetailPage.getSubTotalAmount() + bookingDetailPage.getTax() - bookingDetailPage.getDiscount(),
                        "Total booking is incorrect!!" );

        softAssert.assertEquals(bookingDetailPage.getDueBooking(),
                        bookingDetailPage.getTotalBooking() - bookingDetailPage.getTotalPaidBooking(),
                        "Due booking is incorrect!!" );

        softAssert.assertAll();

    }
}
