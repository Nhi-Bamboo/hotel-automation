package Gwesty.Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CreditCard {
    WebDriver driver;
    String number;
    String  name;
    String date;
    float balance;

    By creditCardNumberLocator = By.xpath("//tr[@class='gradeX odd']/td[1]");
    By ownerNameLocator = By.xpath("//tr[@class='gradeX odd']/td[2]");
    By expiryDateLocator = By.xpath("//tr[@class='gradeX odd']/td[3]");
    By balanceLocator = By.xpath("//tr[@class='gradeX odd']/td[4]");

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard creditCard = (CreditCard) o;
        return Float.compare(balance, creditCard.balance) == 0 && Objects.equals(number, creditCard.number) && Objects.equals(name, creditCard.name) && Objects.equals(date, creditCard.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, date, balance);
    }


    public String getFormatNumber(String creditCardNumber) {
        if (creditCardNumber.length() > 16) {
            creditCardNumber = creditCardNumber.substring(0, 16);
        }
        String cardNumber="";
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

    public static void main(String[] args) {

    }

}
