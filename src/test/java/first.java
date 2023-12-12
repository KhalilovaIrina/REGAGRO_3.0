import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class first {
    @DisplayName("Авторизация")
    void autoVet() {
        $("input[name=email]").setValue("tur@tur.tur");
        $("input[type=password]").setValue("33221100");
        $x("//button[contains(text(),'Авторизация')]").click();
        $x("//button[contains(text(),'Показать карту')]").shouldBe(visible, Duration.ofSeconds(10));
    }

    @DisplayName("Переход с главной страницы на страницу Регистрации животного")
    void getRegistrationPage() {
        $("#accordionMenu > div:nth-child(2)").click();
        $x("//div[contains(text(),'Животное')]").click();
        $x("//button[contains(text(),'Найти другой объект')]").shouldBe(visible, Duration.ofSeconds(10));
    }

    @DisplayName("Первичная регистрация")
    void firstRegistration() {
        // Выбор объекта
        $x("//button[contains(text(),'Найти другой объект')]").click();
        $x("//label[contains(text(),'ИНН')]").click();
        $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input").setValue("4598700098");
        $x("//button[contains(text(),'Найти')]").click();
        $("div[class=item-descritpion]").click();
        $x("//button[contains(text(),'Выбрать')]").click();

        // Вид животного
        $(" #addAnimalForm > div:nth-child(1) > div:nth-child(3) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("#select2--results > li:nth-child(1) > ul > li:nth-child(1)").click();

        // Идентификация
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue("484889594712577");
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Шея");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Дата первичного маркирования

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue("10122023");
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Основное
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(7) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Заявление");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(10) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Белая");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue("14.12.2022");
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        $("input[id=male]").click();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue("Лола");

        // Содержание
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(17) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Пастбищный");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(19) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Навес");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Направление продуктивности
        $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Мясное");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
    }

    void nextRegistration() {
        // Идентификация
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Дата первичного маркирования

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue("10122023");
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Дата Рождения
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue("14.12.2022");
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Кличка
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue("Лола");
    }

    void activateRegistration() {
        $x("//button/div[contains(text(),'Завершить')]").click();

        $("#add-animal-page > div > div.modal.fade.modal-transition.show > div > div > div.modal-body.pt-0 > div > div:nth-child(2) > button").click();
    }

    @BeforeEach
    void setup() {

        open("https://v3.dev.regagro.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void auto() {

    }
}


