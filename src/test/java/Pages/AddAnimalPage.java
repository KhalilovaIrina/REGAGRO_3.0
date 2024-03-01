package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dataGenerator.DataGenerator;
import entities.Animal;
import entities.AnimalGroup;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;
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
    private SelenideElement bees = $("#select2--results > li:nth-child(3) > ul > li:nth-child(3)");
    // private SelenideElement chicken = $("#select2--results > li:nth-child(5) > ul > li:nth-child(1)");
    private SelenideElement krs = $("#select2--results > li:nth-child(1) > ul > li:nth-child(1)");
    private SelenideElement input = $("body > span > span > span.select2-search.select2-search--dropdown > input");
    private SelenideElement identificationNumberField = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col > input");
    private SelenideElement markerPlacesSelection = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(2) > div.col-5 > div > div > div > span > span.selection > span");
    private SelenideElement firstMarkerDate = $("#addAnimalForm > div:nth-child(1) > div:nth-child(6) > div.col-6.mb-3.form-group.pb-3 > div > a > i");
    private SelenideElement firstMarkerDateInput = $("#addAnimalForm > div:nth-child(1) > div:nth-child(6) > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input");
    private SelenideElement foundation = $("#addAnimalForm > div:nth-child(1) > div:nth-child(8) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement suit = $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement birthDate = $("#addAnimalForm > div:nth-child(1) > div:nth-child(12) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input");
    private SelenideElement birthDateFrom = $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div:nth-child(2) > div > input.form-control.flatpickr.input");
    private SelenideElement birthDateBefore = $("#addAnimalForm > div:nth-child(1) > div:nth-child(11) > div > div:nth-child(3) > div > input.form-control.flatpickr.input");
    private SelenideElement birthDateInput = $("#addAnimalForm > div:nth-child(1) > div:nth-child(12) > div > div.col-6.mb-3.form-group.pb-3 > div > input.form-control.flatpickr.input.active");
    private SelenideElement genderFemale = $("input[id=female]");
    private SelenideElement genderMale = $("input[id=male]");
    private SelenideElement genderMixed = $("input[id=mixed_gender]");
    private SelenideElement count = $("#addAnimalForm > div:nth-child(1) > div:nth-child(13) > div > div:nth-child(2) > div > input");
    private SelenideElement countOfMale = $("#addAnimalForm > div:nth-child(1) > div:nth-child(14) > div > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input");
    private SelenideElement countOfFemale = $("#addAnimalForm > div:nth-child(1) > div:nth-child(14) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input");
    private SelenideElement nickName = $("#addAnimalForm > div:nth-child(1) > div:nth-child(16) > div > div:nth-child(2) > div > input");
    private SelenideElement keepTypes = $("#addAnimalForm > div:nth-child(1) > div:nth-child(18) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement keepTypesBees = $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement keepPlaces = $("#addAnimalForm > div:nth-child(1) > div:nth-child(20) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement keepPlacesBees = $("#addAnimalForm > div:nth-child(1) > div:nth-child(17) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement productDirections = $("#product_direction > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement activateButton = $x("//button/div[contains(text(),'Завершить')]");
    private SelenideElement markerTypes = $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > span > span.selection > span");


    private SelenideElement openPassport = $x("//button[contains(., 'Открыть паспорт')]");

    public AddAnimalPage() {
        Selenide.sleep(2500);
        heading.shouldBe(visible);
    }

    public void getActivateRegistrationChickens(Animal chicken) throws SQLException {
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
        input.setValue(chicken.getKind()).pressEnter();

        // Идентификация
        markerTypes.click();
        input.setValue(chicken.getMarkerType()).pressEnter();
        identificationNumberField.click();
        identificationNumberField.setValue(chicken.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.click();
        input.setValue(chicken.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.click();
        firstMarkerDateInput.setValue(chicken.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.click();
        input.setValue(chicken.getRegistrationGround()).pressEnter();

        // Масть
        suit.click();
        input.setValue(chicken.getSuit()).pressEnter();

        // Дата рождения
        birthDate.click();
        birthDateInput.setValue(chicken.getBirthDate()).pressEnter();
        if (chicken.getGender().matches("Самка")) {
            genderFemale.click();
        } else genderMale.click();

        // Кличка
        nickName.setValue(chicken.getNickName()).pressEnter();

        // Тип содержания
        keepTypes.click();
        input.setValue(chicken.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.click();
        input.setValue(chicken.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.click();
        input.setValue(chicken.getProductDirection()).pressEnter();

        // Активация
        activateButton.click();
        Selenide.sleep(2000);
    }

    public void getActivateRegistrationBees(Animal bees) {
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
        input.setValue(bees.getKind()).pressEnter();

        // Идентификация
        markerTypes.click();
        input.setValue(bees.getMarkerType()).pressEnter();
        identificationNumberField.click();
        identificationNumberField.setValue(bees.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.click();
        input.setValue(bees.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.click();
        firstMarkerDateInput.setValue(bees.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.click();
        input.setValue(bees.getRegistrationGround()).pressEnter();

        // Масть
        suit.click();
        input.setValue(bees.getSuit()).pressEnter();

        // Дата заселения улья
        birthDate.click();
        birthDateInput.setValue(bees.getBirthDate()).pressEnter();

        // Тип содержания
        keepTypesBees.click();
        input.setValue(bees.getKeepType()).pressEnter();

        // Место содержания
        keepPlacesBees.click();
        input.setValue(bees.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.click();
        input.setValue(bees.getProductDirection()).pressEnter();

        // Активация
        activateButton.click();
        Selenide.sleep(2000);
    }

    // Регистрация КРС
    public void getActivateRegistrationKRS(Animal krs) {

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
        input.setValue(krs.getKind()).pressEnter();

        // Идентификация
        markerTypes.click();
        input.setValue(krs.getMarkerType()).pressEnter();
        identificationNumberField.click();
        identificationNumberField.setValue(krs.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.click();
        input.setValue(krs.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.click();
        firstMarkerDateInput.setValue(krs.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.click();
        input.setValue(krs.getRegistrationGround()).pressEnter();

        // Масть
        suit.click();
        input.setValue(krs.getSuit()).pressEnter();

        // Дата рождения
        birthDate.click();
        birthDateInput.setValue(krs.getBirthDate()).pressEnter();
        if (krs.getGender().matches("Самка")) {
            genderFemale.click();
        } else genderMale.click();

        // Кличка
        nickName.setValue(krs.getNickName()).pressEnter();

        // Тип содержания
        keepTypes.click();
        input.setValue(krs.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.click();
        input.setValue(krs.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.click();
        input.setValue(krs.getProductDirection()).pressEnter();

        // Активация
        activateButton.click();
        Selenide.sleep(2000);
    }

    // Регистрация группы животных
    public void getActivateRegistrationGroup(AnimalGroup pigs) {

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
        input.setValue(pigs.getKind()).pressEnter();

        // Идентификация
        markerTypes.click();
        input.setValue(pigs.getMarkerType()).pressEnter();
        identificationNumberField.click();
        identificationNumberField.setValue(pigs.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.click();
        input.setValue(pigs.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.click();
        firstMarkerDateInput.setValue(pigs.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.click();
        input.setValue(pigs.getRegistrationGround()).pressEnter();

        // Диапазон дат рождения
        birthDateFrom.click();
        birthDateInput.setValue(pigs.getBirthDateFrom()).pressEnter();

        birthDateBefore.click();
        birthDateInput.setValue(pigs.getBirthDateBefore()).pressEnter();

        //Пол
        if (pigs.getGender().matches("Самка")) {
            genderFemale.click();
        }
        if (pigs.getGender().matches("Смешанный")) {
            genderMixed.click();
        } else genderMale.click();

        // Количество
        count.setValue(pigs.getCount());
        countOfMale.setValue(pigs.getCountOfMale());
        countOfFemale.setValue(pigs.getCountOfFemale());

        // Тип содержания
        keepTypes.click();
        input.setValue(pigs.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.click();
        input.setValue(pigs.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.click();
        input.setValue(pigs.getProductDirection()).pressEnter();

        // Активация
        activateButton.click();
        Selenide.sleep(2000);
    }

    //Переход в паспорт животного после успешной регистрации

    public AnimalPassportPage getAnimalPassportPage() {

        openPassport.shouldBe(visible, Duration.ofSeconds(10));
        openPassport.click();
        return new AnimalPassportPage();

    }


    //Уведомление об успешной регистрации"
    public boolean getMessageSuccessRegistration() {
        Selenide.sleep(2500);
        return $x("//button[contains(., 'Добавить еще')]").isDisplayed();
    }

//Регистрация 'Добавить еще'
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

