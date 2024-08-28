package ru.netology.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderNegativeTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void testEmptyName() {
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void testInvalidName() {
        $("[data-test-id=name] input").setValue("Ekaterina Pedan");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void testEmptyPhone() {
        $("[data-test-id=name] input").setValue("Екатерина Педан");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void testInvalidPhone() {
        $("[data-test-id=name] input").setValue("Екатерина Педан");
        $("[data-test-id=phone] input").setValue("+780055535");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void testNoCheckbox() {
        $("[data-test-id=name] input").setValue("Екатерина-Мария Педан");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }
}
