package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private static SelenideElement card1Selector = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] div");
    private static SelenideElement card2Selector = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d] div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement refresh = $("[data-test-id=action-reload].button");
    private SelenideElement deposit = $("[data-test-id=action-deposit].button");


    public void assertThisIsDashboardPage() {
        heading.shouldBe(Condition.visible);
    }


    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String text = cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }

    public TransactionPage selectCardTransaction (DataHelper.CardInfo cardInfo) {
        cards.findBy(text(cardInfo.getCardNumber().substring(12,16))).$("[data-test-id=action-deposit].button").click();
        return new TransactionPage();
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}


//TODO при переводе с одной карты на эту же карту проходит перевод