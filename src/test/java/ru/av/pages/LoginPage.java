package ru.av.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private static final SelenideElement phoneInput = $x("//input[@id='phone']");
    private static final SelenideElement codeInput = $x("//input[@id='sms-code']");
    private static final SelenideElement submitButton = $x("//button[@class='b-login-form__button']");
    private static final SelenideElement error = $x("//div[@class='b-login-form__error']");

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
@Step("Заполнение номера телефона для входа")
    public LoginPage filPhone(String phone){
        phoneInput.setValue(phone + getRandomNumber(1000,9999));
        submitButton.click();
        return this;
    }

    @Step("Проверка валидационных сообщений")
    public void checkMassage(String error1, String error2){
        for (int i = 0; i < 3; i++) {
            codeInput.setValue(String.valueOf(getRandomNumber(1000,9999)));
            String actualError = error.getText();
            Assertions.assertEquals(actualError,error1);
        }
        codeInput.setValue(String.valueOf(getRandomNumber(1000,9999)));
        String actualError = error.getText();
        Assertions.assertEquals(actualError,error2);
    }
}
