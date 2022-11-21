package ru.av.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.qameta.allure.Allure.step;

public class MainPage {
    private static final ElementsCollection cityList = $$x("//div[@class='popup__container']//div[@class='button_content']");
    private static final SelenideElement saleButton = $x("//div[@class='header-main-bottom_right']//div[2]");
    private static final SelenideElement mainCity = $x("//div[@class='header-main-city_text']");
    @Step("Выбираем город и переходим на страницу Акций")
    public SalePage choiceCityAndTransferSalePage(){
        cityList.first().click();
        saleButton.click();
        return new SalePage();
    }
    @Step("Проверка корректного URL")
     public void checkCurrentURL(String url){
         Assertions.assertEquals(url(),url);
    }

    public void checkCity(String city){
        step("Выбираем город из параметров", () -> {
            cityList.filter(text(city)).first().click();
        });
        step("Проверяем, что применился город из параметров", () -> {
            mainCity.shouldHave(text(city));
        });
    }


}
