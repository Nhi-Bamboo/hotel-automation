import Gwesty.Model.CreditCard;
import Gwesty.Page.AdminPage.*;
import Gwesty.Page.UserPage.HomePage;
import Gwesty.Page.UserPage.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC11 {
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    AddCreditCardPage addCreditCardPage;
    ViewAllCreditCardPage viewAllCreditCardPage;
    String creditCardNumber;
    CreditCard creditCard;
    String ownerName;
    int month;
    int year;
    int cvv;
    float balance;
    Faker faker;
    @BeforeMethod
    public void initData() {
        driver = new EdgeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        addCreditCardPage = new AddCreditCardPage(driver);
        viewAllCreditCardPage = new ViewAllCreditCardPage(driver);
        driver.manage().window().maximize();
        driver.get("http://14.176.232.213:8084/");

        creditCardNumber = faker.finance().creditCard();
        creditCardNumber = creditCard.getFormatNumber(creditCardNumber);
        ownerName = faker.name().firstName();
        month = faker.number().numberBetween(1, 13);
        year = faker.number().numberBetween(2000,2100);
        cvv = faker.number().numberBetween(100,1000);
        balance = faker.number().numberBetween(10000,100000);
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //1. Log in with an admin account.
        homePage.openLoginPage();
        loginPage.login("admin","123456");

        //2. Click [CreditCard]
        homePage.openPageAdmin();
        adminPage.openViewAllCreditCard();

        //3. Click [Add CreditCard]
        viewAllCreditCardPage.clickAddNewButton();

        //4. Input valid all fields.

        System.out.println(creditCardNumber);

        addCreditCardPage.enterCreditCardInformation(creditCardNumber,ownerName,month,year,cvv,balance);
        //5. Click the [SUBMIT] button.
        addCreditCardPage.clickSubmitButton();
        //verify

        CreditCard c = viewAllCreditCardPage.getCreditCardByIndex(1);
        viewAllCreditCardPage.searchByCreditCard(creditCardNumber);

        softAssert.assertEquals(viewAllCreditCardPage.getCreditCardNumber(1),creditCardNumber,"Credit card number khong trung khop");

        softAssert.assertEquals(viewAllCreditCardPage.getOwnerName(1),ownerName.toUpperCase(),"Owner Name khong trung khop!");

        softAssert.assertEquals(viewAllCreditCardPage.getExpiryDate(1),month+"/"+year,"Expiry Date khong trung khop!");

        softAssert.assertEquals(viewAllCreditCardPage.getBalance(1),balance,"Balance khong trung khop!");
        softAssert.assertAll();
    }
}