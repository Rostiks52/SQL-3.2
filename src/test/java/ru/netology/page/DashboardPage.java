package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashboard = $("[data-test-id='dashboard']");

    public DashboardPage() {
        dashboard.shouldBe(Condition.visible);
    }

    public void validFields() {
        dashboard.shouldHave(exactText("Личный кабинет"));
    }
}