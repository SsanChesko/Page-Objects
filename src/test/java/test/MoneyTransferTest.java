package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.TransactionPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    private void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }


    @Test
    void shouldLoginToLC() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем данные пользователя
        var verificationPage = loginPage.validLogin(authInfo); // авторизуемся на странице
        var code = DataHelper.getVerificationCode(authInfo); // получаем из бд код
        var dashboardPage = verificationPage.validCode(code); // вставляем код на странице
        dashboardPage.assertThisIsDashboardPage();
    }

    @Test
    void shouldDepositFirstCard() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем данные пользователя
        var verificationPage = loginPage.validLogin(authInfo); // авторизуемся на странице
        var code = DataHelper.getVerificationCode(authInfo); // получаем из бд код
        var dashboardPage = verificationPage.validCode(code); // вставляем код на странице
        dashboardPage.assertThisIsDashboardPage();

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var transactionSum = DataHelper.randomTransactionFromSecondCard();
//        int amount = 200;

        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCard) + transactionSum;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCard) - transactionSum;


        var deposit = dashboardPage.selectCardTransaction(firstCard);
        deposit.transaction(secondCard, String.valueOf(transactionSum));
        dashboardPage.assertThisIsDashboardPage();

        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);

        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    void shouldDepositSecondCard() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем данные пользователя
        var verificationPage = loginPage.validLogin(authInfo); // авторизуемся на странице
        var code = DataHelper.getVerificationCode(authInfo); // получаем из бд код
        var dashboardPage = verificationPage.validCode(code); // вставляем код на странице
        dashboardPage.assertThisIsDashboardPage();

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var transactionSum = DataHelper.randomTransactionFromFirstCard();
//        int amount = 200;

        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCard) - transactionSum;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCard) + transactionSum;

        var deposit = dashboardPage.selectCardTransaction(secondCard);
        deposit.transaction(firstCard, String.valueOf(transactionSum));
        dashboardPage.assertThisIsDashboardPage();

        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);

        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    @Disabled
    void shouldDepositOverLimit() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем данные пользователя
        var verificationPage = loginPage.validLogin(authInfo); // авторизуемся на странице
        var code = DataHelper.getVerificationCode(authInfo); // получаем из бд код
        var dashboardPage = verificationPage.validCode(code); // вставляем код на странице
        dashboardPage.assertThisIsDashboardPage();

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

//        var transactionSum = DataHelper.randomTransactionFromSecondCard();
        int amount = 20000;

        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCard) + amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCard) - amount;


        var deposit = dashboardPage.selectCardTransaction(firstCard);
        deposit.transaction(secondCard, String.valueOf(amount));
        dashboardPage.assertThisIsDashboardPage();

        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);

        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }
}
