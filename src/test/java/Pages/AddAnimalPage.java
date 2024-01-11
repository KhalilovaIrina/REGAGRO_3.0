package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddAnimalPage {
    private SelenideElement heading = $x("//h2[text()='Регистрация животного']");
    private SelenideElement findObjectButton = $x("//button[contains(text(),'Найти другой объект')]");
    private SelenideElement innCheckbox = $x("//label[contains(text(),'ИНН')]");
    private SelenideElement innField = $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input");
    private SelenideElement findButtonModalWindow = $x("//button[contains(text(),'Найти')]");
    private SelenideElement description = $("div[class=item-descritpion]");
    private SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");
    private SelenideElement animalKind = $(" #addAnimalForm > div:nth-child(1) > div:nth-child(3) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement pig = $("#select2--results > li:nth-child(3) > ul > li:nth-child(1)");
    private SelenideElement krs = $("#select2--results > li:nth-child(1) > ul > li:nth-child(1)");
    private SelenideElement input = $("body > span > span > span.select2-search.select2-search--dropdown > input");
    private SelenideElement identificationNumberField = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input");
    private SelenideElement markerPlacesSelection = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span");
    private SelenideElement firstMarkerDate = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input");
    private SelenideElement firstMarkerDateInput = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active");
    private SelenideElement foundation = $("#addAnimalForm > div:nth-child(1) > div:nth-child(7) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement suit = $("#addAnimalForm > div:nth-child(1) > div:nth-child(10) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement birthDate = $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input");
    private SelenideElement birthDateInput = $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active");
    private SelenideElement genderFemale = $("input[id=female]");
    private SelenideElement nickName = $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input");
    private SelenideElement keepTypes = $("#addAnimalForm > div:nth-child(1) > div:nth-child(17) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement keepPlaces = $("#addAnimalForm > div:nth-child(1) > div:nth-child(19) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement productDirections = $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement activateButton = $x("//button/div[contains(text(),'Завершить')]");
    private SelenideElement markerTypes = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > " +
            "div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button");
    private SelenideElement openPassport = $x("//button[contains(., 'Открыть паспорт')]");


    private void getActivateRegistration(String number) {
        // Выбор объекта
        findObjectButton.click();
        innCheckbox.shouldBe(visible, Duration.ofSeconds(2));
        innCheckbox.click();
        innField.setValue("0278039949");
        findButtonModalWindow.click();
        description.click();
        chooseButton.click();

        // Вид животного
        animalKind.click();

        //Свиньи
        pig.click();
        //КРС
        // krs.click();

        // Идентификация
        markerTypes.click();
//        if (
//                $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > " +
//                        "div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").isDisplayed()
//        ) {
//            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) " +
//                    "> div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span > button").click();
//        }
//
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > " +
//                "div > div:nth-child(1) > div.col > input").click();

        input.setValue("Чип");
        input.pressEnter();
        identificationNumberField.click();
        identificationNumberField.setValue(number);
        identificationNumberField.pressEnter();

        markerPlacesSelection.click();
        input.setValue("холка");
        input.pressEnter();

        // Дата первичного маркирования

        firstMarkerDate.click();
        firstMarkerDateInput.setValue(DataGenerator.getLocalDate());
        firstMarkerDateInput.pressEnter();

        // Основное
        foundation.click();
        input.setValue("Заявление");
        input.pressEnter();

        suit.click();
        input.setValue("рябая");
        input.pressEnter();

        birthDate.click();
        birthDateInput.setValue(DataGenerator.getPastDate());
        birthDateInput.pressEnter();
        genderFemale.click();
        nickName.setValue(DataGenerator.getNickname());

        // Содержание
        keepTypes.click();
        input.setValue("пастбищный");
        input.pressEnter();

        keepPlaces.click();
        input.setValue("навес");
        input.pressEnter();

        // Направление продуктивности
        productDirections.click();
        input.setValue("мясное");
        input.pressEnter();

        // Активация
        activateButton.click();
        Selenide.sleep(2000);
    }

    BasePage basePage = new BasePage();


    public AddAnimalPage() {
        Selenide.sleep(2500);
        heading.shouldBe(visible);
    }

    @DisplayName("Первичная регистрация")
    public void firstRegistration(String number) {

//        if ($("button[class=btn-close]").isDisplayed()) {
//            $("button[class=btn-close]").click();
//        }
        getActivateRegistration(number);
    }

    @DisplayName("Переход паспорт животного после успешной регистрации")

    public AnimalPassportPage getAnimalPassportPage() {

        openPassport.shouldBe(visible, Duration.ofSeconds(10));
        openPassport.click();
        return new AnimalPassportPage();

    }


    @DisplayName("Уведомление об успешной регистрации")
    public boolean getMessageSuccessRegistration() {
        return $x("//button[contains(., 'Добавить еще')]").isDisplayed();
    }

//    @DisplayName("Регистрация 'Добавить еще'")
//    public void nextRegistration() {
//
//        Selenide.sleep(2000);
//        $x("//button[contains(., 'Добавить еще')]").click();
//        getPartOfFields();
//    }

    public String getRandomNumber() {
        return DataGenerator.getNumber(15);
    }


//    private void getPartOfFields() {
//        // Идентификация
//        if (!$("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").isDisplayed()) {
//            firstRegistration();
//        }
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > " +
//                "div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span").click();
//
//        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("Чип");
//        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
//        if ($("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").isDisplayed()) {
//            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").click();
//            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").setValue(DataGenerator.getNumber(15));
//            $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input").pressEnter();
//        } else {
//            firstRegistration();
//        }
//        Selenide.sleep(1000);
//
//        // Место маркирования
//
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 " +
//                "> div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span").click();
//        if (!$("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
//            firstRegistration();
//        }
//        $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("холка");
//        $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
//
//        // Дата первичного маркирования
//
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getLocalDate());
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(5) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();
//
//        // Дата Рождения
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input").click();
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").setValue(DataGenerator.getPastDate());
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active").pressEnter();
//
//        // Кличка
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue(DataGenerator.getNickname());
//
//        // Направление продуктивности
//        $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
//        if ($("body > span > span > span.select2-search.select2-search--dropdown > input").isDisplayed()) {
//            $("body > span > span > span.select2-search.select2-search--dropdown > input").setValue("мясное");
//            $("body > span > span > span.select2-search.select2-search--dropdown > input").pressEnter();
//        } else {
//            firstRegistration();
//        }
//
//        // Активация
//        $x("//button/div[contains(text(),'Завершить')]").click();
//        Selenide.sleep(3000);
//    }

    //    @DisplayName("Многократная регистрация животного")
//    void regIndividualAnimals() {
//        int max = 200;
//        int count = 0;
//        do {
//            helper.nextRegistration();
//            count++;
//        }
//        while (count < max);
//    }
}

