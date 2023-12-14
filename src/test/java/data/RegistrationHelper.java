package data;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationHelper {

    @DisplayName("Авторизация")
    public static void autoVet() {

        $("input[name=email]").shouldBe(visible, Duration.ofSeconds(15));
        $("input[name=email]").setValue("tur@tur.tur");
        $("input[type=password]").setValue("33221100");
        $x("//button[contains(text(),'Авторизация')]").click();
        $x("//button[contains(text(),'Показать карту')]").shouldBe(visible, Duration.ofSeconds(20));
    }

    @DisplayName("Переход с главной страницы на страницу Регистрации животного")
    public void getRegistrationPage() {

        $("#accordionMenu > div:nth-child(2)").click();
        $x("//div[contains(text(),'Животное')]").click();
        $x("//button[contains(text(),'Найти другой объект')]").shouldBe(visible, Duration.ofSeconds(10));
    }

    @DisplayName("Завершение регистрации")
    public void activateRegistration() {

        $x("//button/div[contains(text(),'Завершить')]").click();
        Selenide.sleep(3000);
        $x("//button[contains(., 'Добавить еще')]").click();
//        if ($x("//button[contains(., 'Добавить еще')]").isDisplayed()){
//            Selenide.sleep(3000);
//            $x("//button[contains(., 'Добавить еще')]").click();
//        }
    }

    @DisplayName("Первичная регистрация")
    public void firstRegistration() {

        if ($("button[class=btn-close]").isDisplayed()) {
            $("button[class=btn-close]").click();
        }
        getRegistrationPage();
        getAllFields();
    }

    @DisplayName("Регистрация 'Добавить еще'")
    public void nextRegistration() {

        Selenide.sleep(2000);
        getPartOfFields();
    }

    private void getAllFields() {
        // Выбор объекта
        $x("//button[contains(text(),'Найти другой объект')]").click();
        $x("//label[contains(text(),'ИНН')]").shouldBe(visible, Duration.ofSeconds(2));
        $x("//label[contains(text(),'ИНН')]").click();
        $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input").setValue("0278039949");
        $x("//button[contains(text(),'Найти')]").click();
        $("div[class=item-descritpion]").click();
        $x("//button[contains(text(),'Выбрать')]").click();

        // Вид животного
        $(" #addAnimalForm > div:nth-child(1) > div:nth-child(3) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();

        //Свиньи
        // $("#select2--results > li:nth-child(3) > ul > li:nth-child(1)").click();
        //КРС
        $("#select2--results > li:nth-child(1) > ul > li:nth-child(1)").click();

        // Идентификация
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > " +
                "div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();

        if (
                $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > " +
                        "div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").isDisplayed()
        ) {
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) " +
                    "> div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();
        }

        if (!$("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > " +
                "div > div:nth-child(1) > div.col > input").isDisplayed()) {
            firstRegistration();
        }

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > " +
                "div > div:nth-child(1) > div.col > input").click();

        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
        if ($("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").isDisplayed()) {
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue(DataGenerator.getNumber(15));
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();
        } else {
            firstRegistration();
        }

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
        if (!$("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
            firstRegistration();
        }
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("шея");
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
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("чалая");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getPastDate());
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();
        $("input[id=male]").click();
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue(DataGenerator.getNickname());

        // Содержание
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(17) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("пастбищный");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(19) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("навес");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        // Направление продуктивности
        $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
        if (!$("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
            firstRegistration();
        }
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("мясное");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();

        activateRegistration();
    }

    private void getPartOfFields() {
        // Идентификация
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > " +
                "div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span").click();
        if (!$("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").isDisplayed()) {
            firstRegistration();
        }
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
        if ($("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").isDisplayed()) {
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue(DataGenerator.getNumber(15));
            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();
        } else {
            firstRegistration();
        }
        Selenide.sleep(1000);

        // Место маркирования

        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 " +
                "> div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
        if (!$("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
            firstRegistration();
        }
        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("шея");
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
        if ($("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
            $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("мясное");
            $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
        } else {
            firstRegistration();
        }
        activateRegistration();
    }
}