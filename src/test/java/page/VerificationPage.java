package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement nextButton = $("[data-test-id=action-verify]");

    public DashboardPage validCode(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        nextButton.click();
        return new DashboardPage();
    }

}
