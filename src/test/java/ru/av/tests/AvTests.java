package ru.av.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.av.data.TestData;
import ru.av.pages.MainPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("Проверка валидационных сообщений на странице входа")
    public void checkValidationMessages() {
        mainPage
                .transferLoginPage()
                .filPhone(testData.getPhoneNumber())
                .checkMassage(testData.getError_01(), testData.getError_02());
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину")
    public void checkItemAddingToCart() {
        mainPage
                .searchItem(testData.getProduct())
                .addItem(testData.getProduct())
                .selectStoreAddress(testData.getAddress())
                .goToCart()
                .checkItemInCart(testData.getProduct(), testData.getAddress());

    }

    @Test
    @DisplayName("Проверка поиска товара в каталоге")
    public void searchProductsInCatalog() {
        mainPage
                .searchItem(testData.getProduct())
                .checkProducts(testData.getProduct());
    }

}
