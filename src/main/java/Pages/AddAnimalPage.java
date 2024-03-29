package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import entities.Animal;
import entities.AnimalGroup;

import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddAnimalPage {
    private final SelenideElement findObjectButton = $x("//button[contains(text(),'Найти другой объект')]");
    private final SelenideElement innCheckbox = $x("//label[contains(text(),'ИНН')]");
    private final SelenideElement innField = $x("//small[contains(text(), 'ИНН')]/following-sibling::input");
    private final SelenideElement findButtonModalWindow = $x("//button[contains(text(),'Найти')]");
    private final SelenideElement description = $("div[class=item-descritpion]");
    private final SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");
    private final SelenideElement animalKind = $x("//div[contains(text(),'Вид животного')]/..//span[contains(@class,'select2-container--bootstrap-5')]");
    private final SelenideElement input = $x("//input[@class='select2-search__field']");
    private final SelenideElement identificationNumberField = $x("//div/input[@placeholder='Введите номер']");
    private final SelenideElement markerPlacesSelection = $x("//select[@name='marker_place_id']/following-sibling::span[@dir='ltr']");
    private final SelenideElement firstMarkerDate = $x("//div[contains(text(), 'Дата первичного маркирования')]/following-sibling::div//i[@class='bi bi-calendar3']");
    private final SelenideElement firstMarkerDateInput = $x("//input[@class='form-control flatpickr input active']");
    private final SelenideElement foundation = $x("//div[contains(text(), 'Основание')]/following-sibling::div//span[@dir='ltr']");
    private final SelenideElement suit = $x("//div[contains(text(), 'Масть')]/following-sibling::div//span[@dir='ltr']");
    private final SelenideElement birthDate = $x("//div[contains(text(), 'Дата рождения')]/following-sibling::div//i[@class='bi bi-calendar3']");
    private final SelenideElement birthDateFrom = $x("//input[@name='birth_date_from']/following-sibling::input[@placeholder='Выберите дату']");
    private final SelenideElement birthDateBefore = $x("//input[@name='birth_date_to']/following-sibling::input[@placeholder='Выберите дату']");
    private final SelenideElement birthDateInput = $x("//input[@class='form-control flatpickr input active']");
    private final SelenideElement genderFemale = $("input[id=female]");
    private final SelenideElement genderMale = $("input[id=male]");
    private final SelenideElement genderMixed = $("input[id=mixed_gender]");
    private final SelenideElement count = $x("//input[@name='count']");
    private final SelenideElement countOfMale = $x("//div[contains(text(), 'Самцы')]/..//input");
    private final SelenideElement countOfFemale = $x("//div[contains(text(), 'Самки')]/..//input");
    private final SelenideElement nickName = $x("//div[contains(text(), 'Кличка')]/following::input[@type='text']");
    private final SelenideElement keepTypes = $x("//div[contains(text(),'Способ содержания')]/following-sibling::div//span[@dir='ltr']");
    private final SelenideElement keepPlaces = $x("//div[contains(text(), 'Место содержания')]/following-sibling::div//span[@dir='ltr']");
    private final SelenideElement productDirections = $x("//div[contains(text(), 'продуктивности')]/following-sibling::div//span[@dir='ltr']");
    private final SelenideElement activateButton = $x("//button/div[contains(text(),'Завершить')]");
    private final SelenideElement markerTypes = $x("//select[@name='marker_type_id']/following-sibling::span//span[@aria-labelledby='select2--container']");
    private final SelenideElement openPassport = $x("//button[contains(., 'Открыть паспорт')]");

    public AddAnimalPage() {
        Selenide.sleep(2500);
        SelenideElement heading = Selenide.$x("//h2[contains(text(), 'Регистрация')]");
        heading.shouldBe(Condition.visible);
    }

    public void getActivateRegistrationChickens(Animal chicken) throws SQLException {
        // Выбор объекта
        findObjectButton.should(Condition.enabled).click();
        innCheckbox.shouldBe(Condition.visible, Duration.ofSeconds(2));
        innCheckbox.should(Condition.enabled).click();
        innField.setValue("0278039949");
        findButtonModalWindow.should(Condition.enabled).click();
        description.should(Condition.enabled).click();
        chooseButton.should(Condition.enabled).click();

        // Вид животного
        animalKind.should(Condition.enabled).click();
        input.setValue(chicken.getKind()).pressEnter();

        // Идентификация
        markerTypes.should(Condition.enabled).click();
        input.setValue(chicken.getMarkerType()).pressEnter();
        identificationNumberField.should(Condition.enabled).click();
        identificationNumberField.setValue(chicken.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.should(Condition.enabled).click();
        input.setValue(chicken.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.should(Condition.enabled).click();
        firstMarkerDateInput.setValue(chicken.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.should(Condition.enabled).click();
        input.setValue(chicken.getRegistrationGround()).pressEnter();

        // Масть
        suit.should(Condition.enabled).click();
        input.setValue(chicken.getSuit()).pressEnter();

        // Дата рождения
        birthDate.should(Condition.enabled).click();
        birthDateInput.setValue(chicken.getBirthDate()).pressEnter();
        if (chicken.getGender().matches("Самка")) {
            genderFemale.should(Condition.enabled).click();
        } else genderMale.should(Condition.enabled).click();

        // Кличка
        nickName.setValue(chicken.getNickName()).pressEnter();

        // Тип содержания
        keepTypes.should(Condition.enabled).click();
        input.setValue(chicken.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.should(Condition.enabled).click();
        input.setValue(chicken.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.should(Condition.enabled).click();
        input.setValue(chicken.getProductDirection()).pressEnter();

        // Активация
        activateButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
    }

//    public void getActivateRegistrationBees(Animal bees) {
//        // Выбор объекта
//        findObjectButton.click();
//        innCheckbox.shouldBe(Condition.visible, Duration.ofSeconds(2));
//        innCheckbox.click();
//        innField.setValue("0278039949");
//        findButtonModalWindow.click();
//        description.click();
//        chooseButton.click();
//
//        // Вид животного
//        animalKind.click();
//        input.setValue(bees.getKind()).pressEnter();
//
//        // Идентификация
//        markerTypes.click();
//        input.setValue(bees.getMarkerType()).pressEnter();
//        identificationNumberField.click();
//        identificationNumberField.setValue(bees.getIdentificationNumber()).pressEnter();
//        markerPlacesSelection.click();
//        input.setValue(bees.getMarkerPlace()).pressEnter();
//
//        // Дата первичного маркирования
//        firstMarkerDate.click();
//        firstMarkerDateInput.setValue(bees.getFirstMarkerDate()).pressEnter();
//
//        // Основание
//        foundation.click();
//        input.setValue(bees.getRegistrationGround()).pressEnter();
//
//        // Масть
//        suit.click();
//        input.setValue(bees.getSuit()).pressEnter();
//
//        // Дата заселения улья
//        birthDate.click();
//        birthDateInput.setValue(bees.getBirthDate()).pressEnter();
//
//        // Тип содержания
//        keepTypes.click();
//        input.setValue(bees.getKeepType()).pressEnter();
//
//        // Место содержания
//        keepPlaces.click();
//        input.setValue(bees.getKeepPlace()).pressEnter();
//
//        // Направление продуктивности
//        productDirections.click();
//        input.setValue(bees.getProductDirection()).pressEnter();
//
//        // Активация
//        activateButton.click();
//        Selenide.sleep(2000);
//    }

    // Регистрация КРС
    public void getActivateRegistrationKRS(Animal krs) {

        // Выбор объекта
        findObjectButton.should(Condition.enabled).click();
        innCheckbox.shouldBe(Condition.visible, Duration.ofSeconds(2));
        innCheckbox.should(Condition.enabled).click();
        innField.setValue("0278039949");
        findButtonModalWindow.should(Condition.enabled).click();
        description.should(Condition.enabled).click();
        chooseButton.should(Condition.enabled).click();

        // Вид животного
        animalKind.should(Condition.enabled).click();
        input.setValue(krs.getKind()).pressEnter();

        // Идентификация
        markerTypes.should(Condition.enabled).click();
        input.setValue(krs.getMarkerType()).pressEnter();
        identificationNumberField.should(Condition.enabled).click();
        identificationNumberField.setValue(krs.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.should(Condition.enabled).click();
        Selenide.sleep(1500);
        input.setValue(krs.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.should(Condition.enabled).click();
        firstMarkerDateInput.setValue(krs.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.should(Condition.enabled).click();
        input.setValue(krs.getRegistrationGround()).pressEnter();

        // Масть
        suit.should(Condition.enabled).click();
        input.setValue(krs.getSuit()).pressEnter();

        // Дата рождения
        birthDate.should(Condition.enabled).click();
        birthDateInput.setValue(krs.getBirthDate()).pressEnter();
        if (krs.getGender().matches("Самка")) {
            genderFemale.should(Condition.enabled).click();
        } else genderMale.should(Condition.enabled).click();

        // Кличка
        nickName.setValue(krs.getNickName()).pressEnter();

        // Тип содержания
        keepTypes.should(Condition.enabled).click();
        Selenide.sleep(1500);
        input.setValue(krs.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.should(Condition.enabled).click();
        input.setValue(krs.getKeepPlace());
        Selenide.sleep(1000);
        input.pressEnter();

        // Направление продуктивности
        productDirections.should(Condition.enabled).click();
        input.setValue(krs.getProductDirection()).pressEnter();

        // Активация
        activateButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
    }

    // Регистрация группы животных
    public void getActivateRegistrationGroup(AnimalGroup pigs) {

        // Выбор объекта
        findObjectButton.should(Condition.enabled).click();
        innCheckbox.shouldBe(Condition.visible, Duration.ofSeconds(2));
        innCheckbox.should(Condition.enabled).click();
        innField.setValue("0278039949");
        findButtonModalWindow.should(Condition.enabled).click();
        description.should(Condition.enabled).click();
        chooseButton.should(Condition.enabled).click();

        // Вид животного
        animalKind.should(Condition.enabled).click();
        input.setValue(pigs.getKind()).pressEnter();

        // Идентификация
        markerTypes.should(Condition.enabled).click();
        input.setValue(pigs.getMarkerType()).pressEnter();
        identificationNumberField.should(Condition.enabled).click();
        identificationNumberField.setValue(pigs.getIdentificationNumber()).pressEnter();
        Selenide.sleep(1500);
        markerPlacesSelection.should(Condition.enabled).click();
        input.setValue(pigs.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.click();
        firstMarkerDateInput.setValue(pigs.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.should(Condition.enabled).click();
        input.setValue(pigs.getRegistrationGround()).pressEnter();

        // Диапазон дат рождения

        birthDateFrom.should(Condition.enabled).click();
        birthDateInput.setValue(pigs.getBirthDateFrom()).pressEnter();

        birthDateBefore.should(Condition.enabled).click();
        birthDateInput.setValue(pigs.getBirthDateBefore()).pressEnter();

        //Пол
        if (pigs.getGender().matches("Самка")) {
            genderFemale.click();
            countOfFemale.setValue(pigs.getCountOfFemale());
        }
        if (pigs.getGender().matches("Самцы")) {
            genderMale.click();
            countOfMale.setValue(pigs.getCountOfMale());
        } else
            genderMixed.click();
        countOfMale.setValue(pigs.getCountOfMale());
        countOfFemale.setValue(pigs.getCountOfFemale());

        // Количество
        count.setValue(pigs.getCount());

        // Тип содержания
        keepTypes.should(Condition.enabled).click();
        input.setValue(pigs.getKeepType()).pressEnter();

        // Место содержания
        keepPlaces.should(Condition.enabled).click();
        input.setValue(pigs.getKeepPlace()).pressEnter();

        // Направление продуктивности
        productDirections.should(Condition.enabled).click();
        input.setValue(pigs.getProductDirection()).pressEnter();

        // Активация
        activateButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
    }

    public void getActivateRegistrationBees(AnimalGroup bees) {
        // Выбор объекта
        findObjectButton.click();
        innCheckbox.shouldBe(Condition.visible, Duration.ofSeconds(2));
        innCheckbox.click();
        innField.setValue("0278039949");
        findButtonModalWindow.click();
        description.click();
        chooseButton.click();

        // Вид животного
        animalKind.should(Condition.enabled).click();
        input.setValue(bees.getKind()).pressEnter();

        // Идентификация
        markerTypes.should(Condition.enabled).click();
        input.setValue(bees.getMarkerType()).pressEnter();
        identificationNumberField.should(Condition.enabled).click();
        identificationNumberField.setValue(bees.getIdentificationNumber()).pressEnter();
        markerPlacesSelection.should(Condition.enabled).click();
        input.setValue(bees.getMarkerPlace()).pressEnter();

        // Дата первичного маркирования
        firstMarkerDate.should(Condition.enabled).click();
        firstMarkerDateInput.setValue(bees.getFirstMarkerDate()).pressEnter();

        // Основание
        foundation.should(Condition.enabled).click();
        input.setValue(bees.getRegistrationGround()).pressEnter();

        // Дата заселения улья
        birthDateFrom.should(Condition.enabled).click();
        birthDateInput.should(Condition.enabled).setValue(bees.getBirthDateFrom()).pressEnter();

        // Тип содержания
        keepTypes.should(Condition.enabled).click();
        input.setValue(bees.getKeepType()).pressEnter();

        // Направление продуктивности
        productDirections.should(Condition.enabled).click();
        input.setValue(bees.getProductDirection()).pressEnter();

        // Активация
        activateButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
    }

    //Переход в паспорт животного после успешной регистрации

    public void getAnimalPassportPage() {

        openPassport.shouldBe(Condition.visible, Duration.ofSeconds(10));
        openPassport.click();
        new AnimalPassportPage();

    }

    public void getAnimalGroupPassportPage() {

        openPassport.shouldBe(Condition.visible, Duration.ofSeconds(10));
        openPassport.click();
        new AnimalGroupPassportPage();

    }


    //Уведомление об успешной регистрации"
    public boolean getMessageSuccessRegistration() {
        Selenide.sleep(2500);
        return Selenide.$x("//button[contains(., 'Добавить еще')]").isDisplayed();
    }

//Регистрация 'Добавить еще'
//    public void nextRegistration() {
//
//        Selenide.sleep(2000);
//        $x("//button[contains(., 'Добавить еще')]").click();
//        getPartOfFields();
//    }
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

