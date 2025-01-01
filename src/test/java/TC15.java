import Gwesty.Model.GuestInRoom;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TC15 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    BookingPage bookingPage;
    BookingDetailPage bookingDetailPage;
    AddGuestDetailsPage addGuestDetailsPage;
    RoomPage roomPage;
    RoomDetailPage roomDetailPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    Faker faker;
    GuestInRoom guestInRoom;

    String idBooking;
    String fullName;
    String address;
    int idNumber;
    String gender;
    String dateOfBirth;
    Date date;

    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();

        bookingPage = new BookingPage(driver);
        bookingDetailPage = new BookingDetailPage(driver);
        addGuestDetailsPage = new AddGuestDetailsPage(driver);
        roomPage = new RoomPage(driver);
        roomDetailPage = new RoomDetailPage(driver);
        bookNowPage = new BookNowPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmPage = new ConfirmPage(driver);
        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        //"Pre-condition: Users have been booked a room
        homePage.openLoginPage();
        loginPage.login("thuongnth","123456");

        homePage.selectRoomPage();
        roomPage.openDetailRoomByIndex(1);

        roomDetailPage.bookingRoom("2025/12/30","2025/12/31",1,0);
        bookNowPage.checkCheckBoxAgree();
        bookNowPage.clickSubmitButton();

        checkoutPage.paymentByCreditCard("9999 9999 9999 9999","THUONG","10 / 10",999);

        idBooking = confirmPage.getIDBooking();
        homePage.Logout();

        fullName = faker.name().fullName();
        gender= faker.demographic().sex();
        address = faker.address().country();
        idNumber = faker.number().numberBetween(100,999);
        date =  faker.date().past(3650, java.util.concurrent.TimeUnit.DAYS);
        dateOfBirth = String.valueOf(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        guestInRoom = new GuestInRoom();
        guestInRoom.setRoom(0);
        guestInRoom.setId(idNumber);
        guestInRoom.setFullName(fullName);
        guestInRoom.setGender(gender);
        guestInRoom.setDateOfBirth(dateOfBirth);
        guestInRoom.setAddress(address);
        guestInRoom.setType("ID CARD");

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
    //Verify that add guest in room successfully when input valid all fields
        //1. Log in
        homePage.openLoginPage();
        loginPage.login("admin","123456");

        //2. Click [Booking]
        homePage.openPageAdmin();
        adminPage.openBookingPage();

        //3. Click [Eye icon] button
        bookingPage.searchByID(idBooking);
        bookingPage.openBookingDetail();

        //4. Click [Guest In Room]
        bookingDetailPage.clickGuestInRoom();

        //5. Click [ADD NEW +] button
        bookingDetailPage.clickAddNewGuestInRoomButtonLocator();

        //6. Input valid all fields.
        addGuestDetailsPage.addGuestInRoomInformation(guestInRoom);
        guestInRoom.setRoom(addGuestDetailsPage.getRoom());
        guestInRoom.setType(null);
        //7. Click the [SUBMIT] button.
        addGuestDetailsPage.clickSubmitButton();

        //8. Click [Guest In Room] in Booking Details
        bookingDetailPage.clickGuestInRoom();

        //9.Click [Guest In Room] in siderbar menu
        GuestInRoom viewGuestInRoom = bookingDetailPage.getGuestInRoomByIndex(1);

        softAssert.assertEquals(viewGuestInRoom,guestInRoom,"Khong trung khop!");
        softAssert.assertAll();
    }
}

