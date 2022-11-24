package ru.av.data;

import lombok.Data;
import lombok.Getter;

@Data
public class TestData {
    private final String baseURL = "https://av.ru/";
    private final String phoneNumber = "7777777";
    private final String error_01 = "Введите код еще раз.";
    private final String error_02 = "Получите новый код и введите его заново.";
    private final String product = "Банан";
    private final String address = "Никольская";

}
