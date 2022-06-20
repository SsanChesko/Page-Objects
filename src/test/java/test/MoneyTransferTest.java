package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

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

    public void loginToLC() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // получаем данные пользователя
        var verificationPage = loginPage.validLogin(authInfo); // авторизуемся на странице
        var code = DataHelper.getVerificationCode(authInfo); // получаем из бд код
        var dashboardPage = verificationPage.validCode(code); // вставляем код на странице
        dashboardPage.assertThisIsDashboardPage();
    }

    @Test
    void shouldTransferMoneyFrom2To1() {
        loginToLC();
        var dashboardPage = new DashboardPage();
        $$("[data-test-id=action-deposit].button").get(0).click();
        $("[data-test-id='amount'] input").val("10");
        $("[data-test-id='from'] input").val(DashboardPage.secondCard());
        $(withText("Пополнить")).click();
        dashboardPage.assertThisIsDashboardPage();
    }

    @Test
    void shouldTransferMoneyFrom1To2() {
        loginToLC();
        var dashboardPage = new DashboardPage();
        $$("[data-test-id=action-deposit].button").get(1).click();
        $("[data-test-id='amount'] input").val(String.valueOf(dashboardPage.money()));
        $("[data-test-id='from'] input").val(DashboardPage.firstCard());
        $(withText("Пополнить")).click();
        dashboardPage.assertThisIsDashboardPage();
    }
}
