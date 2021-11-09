package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static java.time.Duration.ofSeconds;

public class VerificationPage {
    private SelenideElement code = $("[data-test-id=code] input");
    private SelenideElement button = $("[data-test-id=action-verify]");
    private SelenideElement errorNotif = $("[data-test-id=error-notification]");


    public VerificationPage() {
        code.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        code.setValue(verificationCode);
        button.click();
        return new DashboardPage();
    }

    public void incorrectVerify(String verificationCode) {
        code.setValue(verificationCode);
        sleep(15000);
        button.click();
        errorNotif.shouldBe(visible, ofSeconds(15));
        $("[data-test-id=error-notification]>.notification__title")
                .shouldHave(text("Ошибка"));
        $("[data-test-id=error-notification]>.notification__content")
                .shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}