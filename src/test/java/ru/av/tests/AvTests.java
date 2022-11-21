package ru.av.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.av.data.TestData;
import ru.av.pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на сайт Азбуки Вкуса ")
public class AvTests extends TestBase {
    TestData testData = new TestData();
    MainPage mainPage = new MainPage();


    @DisplayName("Проверка выбора текущего местоположения.")
    @ParameterizedTest(name = "Город - {0}.")
    @ValueSource(strings = {"Москва", "Санкт-Петербург"})
    public void checkCurrentCity(String city) {
       mainPage.checkCity(city);
    }


    @Test
    @DisplayName("Проверка ограничения контента по возрасту. Нажатие на кнопку - Нет.")
    public void сheckContentAgeRestriction() {
        mainPage
                .choiceCityAndTransferSalePage()
                .transferToMainPage()
                .checkCurrentURL(testData.getBaseURL());
    }
}