package ru.av.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SalePage {
    private static final SelenideElement popup = $x("//div[@class='popup__container']");
    private static final SelenideElement noButton = $x("//div[@class='button_content'][contains(text(),'Нет')]");

    @Step("Переходим на главную страницу")
    public MainPage transferToMainPage() {
        popup.shouldBe(visible);
        noButton.click();
        return new MainPage();
    }
}
