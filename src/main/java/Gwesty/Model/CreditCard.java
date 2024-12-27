package Gwesty.Model;

import Gwesty.Page.AdminPage.AddCreditCardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CreditCard {
    String number="";
    String name;
    int month;
    int year;
    int cvv;
    double balance;


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


    public Integer getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard creditCard = (CreditCard) o;
        return Double.compare(balance, creditCard.balance) == 0 &&
                Integer.compare(cvv, creditCard.cvv) == 0 &&
                Objects.equals(number, creditCard.number) &&
                Objects.equals(name, creditCard.name) &&
                Integer.compare(month, creditCard.month) == 0 &&
                Integer.compare(year, creditCard.year) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, month, year, cvv, balance);
    }

    public String getFormatNumber(String creditCardNumber) {
        if (creditCardNumber.length() > 16) {
            creditCardNumber = creditCardNumber.substring(0, 16);
        }
        creditCardNumber = creditCardNumber.replaceAll("\\D", "");
        for (int i = 0; i < creditCardNumber.length(); i = i + 4) {
            if (i + 4 <= creditCardNumber.length()) {
                number += creditCardNumber.substring(i, i + 4) + " ";
            } else {
                number += creditCardNumber.substring(i); // Xử lý phần dư
            }
        }
        return number;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", month=" + month +
                ", year=" + year +
                ", cvv=" + cvv +
                ", balance=" + balance +
                '}';
    }
}
