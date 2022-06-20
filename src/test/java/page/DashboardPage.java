package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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


    public void assertThisIsDashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(/*Cards cardSelectors*/) {
        String text = "a";
        return extractBalance(text);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int money () {
        int money1;
        return money1 = getFirstCardBalance();
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public static String firstCard() {
        String firstCard = "5559 0000 0000 0001";
        return firstCard;
    }

    public static String secondCard() {
        String secondCard = "5559 0000 0000 0002";
        return secondCard;
    }

    public int transaction() {
        int a = 0;
        int b = getCardBalance();
        int x = a + (int) (Math.random() * ((b - a) + 1));
        return x;
    }


}


//TODO при переводе с одной карты на эту же карту проходит перевод