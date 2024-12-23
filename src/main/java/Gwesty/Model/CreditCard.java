package Gwesty.Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreditCard {
    WebDriver driver;
    String cardNumber="";
    String  name;
    String month;

    By creditCardNumberLocator = By.xpath("//tr[@class='gradeX odd']/td[1]");
    By ownerNameLocator = By.xpath("//tr[@class='gradeX odd']/td[2]");
    By expiryDateLocator = By.xpath("//tr[@class='gradeX odd']/td[3]");
    By balanceLocator = By.xpath("//tr[@class='gradeX odd']/td[4]");

    public String getNumber() {
        return cardNumber;
    }

    public String getFormatNumber(String creditCardNumber) {
        if (creditCardNumber.length() > 16) {
            creditCardNumber = creditCardNumber.substring(0, 16);
        }
        creditCardNumber = creditCardNumber.replaceAll("\\D", "");
        for (int i = 0; i < creditCardNumber.length(); i = i + 4) {
            if (i + 4 <= creditCardNumber.length()) {
                cardNumber += creditCardNumber.substring(i, i + 4) + " ";
            } else {
                cardNumber += creditCardNumber.substring(i); // Xử lý phần dư
            }
        }
        return cardNumber;
    }



}
