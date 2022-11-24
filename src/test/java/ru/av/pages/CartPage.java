package ru.av.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class CartPage {
    private static final SelenideElement productInfo = $x("//a[contains(@class,'cart-product-desc_name')]");
    private static final SelenideElement addressInfo = $x("//div[@class='cart-address_info']");

    @Step("Проверяем название добавленного товара в корзине")
    public void checkItemInCart(String item, String address) {
        String actualItem = productInfo.getText();
        String actualAddress = addressInfo.getText();
        assertTrue(actualItem.contains(item));
        assertTrue(actualAddress.contains(address));
    }
}
