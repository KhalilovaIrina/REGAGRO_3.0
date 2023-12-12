import com.codeborne.selenide.Configuration;
import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class first {

    boolean isVisible() {
        $("#add-animal-page > div > div.modal.fade.modal-transition.show > div > div > div.modal-body.pt-0 > div > div:nth-child(2) > button").shouldBe(visible, Duration.ofSeconds(2));
        return true;
    }

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

    void activateRegistration() {
        $x("//button/div[contains(text(),'Завершить')]").click();

        $("#add-animal-page > div > div.modal.fade.modal-transition.show > div > div > div.modal-body.pt-0 > div > div:nth-child(2) > button").click();
    }

    @DisplayName("Первичная регистрация")
    void firstRegistration() {
        // Выбор объекта
        $x("//button[contains(text(),'Найти другой объект')]").click();
        $x("//label[contains(text(),'ИНН')]").click();
        $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input").setValue("0278039949");
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
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue(DataGenerator.getNumber(15));
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Шея");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Дата первичного маркирования

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getLocalDate());
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Основное
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(7) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Заявление");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(10) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Желтая");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getPastDate());
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        $("input[id=female]").click();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue(DataGenerator.getNickname());

        // Содержание
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(17) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Беспривязный");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(19) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Загон");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Направление продуктивности
        $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Племенное");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
    }

    void nextRegistration() {

        // Идентификация
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue(DataGenerator.getNumber(15));
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Холка");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Дата первичного маркирования

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getLocalDate());
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Дата Рождения
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getPastDate());
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();

        // Кличка
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue(DataGenerator.getNickname());

        // Направление продуктивности
        $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Молочное");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        activateRegistration();

    }


    @BeforeEach
    void setup() {

        open("https://v3.dev.regagro.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void regIndividualAnimals() {
        int max = 20;
        int count = 0;

        autoVet();
        getRegistrationPage();
        firstRegistration();
        activateRegistration();

        do {
            if ($("#add-animal-page > div > div.modal.fade.modal-transition.show > div > div > div.modal-body.pt-0 > div > div:nth-child(2) > button").isDisplayed()) {
                $("#add-animal-page > div > div.modal.fade.modal-transition.show > div > div > div.modal-header > button").click();
            } else
                nextRegistration();
            count++;
        }
        while (count < max);

    }
}


