package ru.av.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogPage {
    private static final SelenideElement addToCartButton = $x("//div[@data-digi-prod-position='1']//div[@class='add-to-cart']");
    private static final SelenideElement selfDeliveryButton = $x("//div[@class='common-switch-tabs_tab']");
    private static final SelenideElement addressInput = $x("//input[@placeholder='Выберите адрес магазина']");
    private static final SelenideElement foundAddress = $x("//div[@class='delivery-form__suggestions']//div[1]");
    private static final SelenideElement todayTimeSlot = $x("//div[@class='timeslot'][1]");
    private static final SelenideElement applyButton = $x("//div[contains(text(),'Сегодня, за 60 минут')]");
    private static final SelenideElement menuCartButton = $x("//div[contains(text(),'Корзина')]");
    private static final SelenideElement cartButton = $x("//div[contains(text(),'В корзину')]");
    private static final SelenideElement productInfo = $x("//div[@data-digi-prod-position='1']//div[@class='product-info']");
    private final ElementsCollection productList = $$x("//div[@class='catalog-products_list']//div[@class='product-info_name']//a");

    @Step("Нажимаем на кнопку добавления товара в корзину")
    public CatalogPage addItem(String item) {
        productInfo.shouldHave(text(item));
        addToCartButton.click();
        return this;
    }

    @Step("Выбираем адрес магазина для самовывоза товара")
    public CatalogPage selectStoreAddress(String address) {
        selfDeliveryButton.click();
        addressInput.setValue(address);
        foundAddress.click();
        todayTimeSlot.click();
        applyButton.click();
        menuCartButton.click();
        cartButton.click();
        return this;
    }

    @Step("Переходим в корзину")
    public CartPage goToCart() {
        menuCartButton.click();
        cartButton.click();
        return new CartPage();
    }

    @Step("Проверка найденного товара")
    public void checkProducts(String item) {
        List<String> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(productList.get(i).getAttribute("title"));
            assertTrue(products.get(i).contains(item));
        }
    }
}
