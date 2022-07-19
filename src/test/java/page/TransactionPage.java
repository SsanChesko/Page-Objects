package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement sumField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement buttonAdd = $("[data-test-id='action-transfer']");
    private SelenideElement buttonCancel = $("[data-test-id='action-cancel");

    public DashboardPage transaction (DataHelper.CardInfo cardInfo, String sum) {
        sumField.setValue(sum);
        fromField.setValue(cardInfo.getCardNumber());
        buttonAdd.click();
        return new DashboardPage();
    }

    public DashboardPage transactionWError (DataHelper.CardInfo cardInfo, String sum) {
        sumField.setValue(sum);
        fromField.setValue(cardInfo.getCardNumber());
        buttonAdd.click();
        $(".notification__content").shouldHave(Condition.text("Максимально допустимый перевод + "));
        return new DashboardPage();
    }
}
