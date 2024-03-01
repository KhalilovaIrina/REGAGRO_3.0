package tests;

import Pages.AddAnimalPage;
import Pages.AddEnterprisePage;
import Pages.AnimalPassportPage;
import Pages.AuthPage;
import Pages.BasePage;
import Pages.EnterpriseCardPage;
import Pages.HomePage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import entities.Animal;
import entities.AnimalGroup;
import helpers.AnimalFactory;
import helpers.DBHelper;
import dataGenerator.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegressTests {
    @BeforeAll
    static void setUpAll() {
       // Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://v3.dev.regagro.ru/");
        AuthPage.autoVet();
        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }

    @AfterEach
    public void getHomePage() {
        BasePage basePage = new BasePage();
        basePage.getHomePage();
    }

    HomePage homePage = new HomePage();
    DBHelper dbHelper = new DBHelper();

    @DisplayName("RAT-1964 Выход из профиля пользователя и RAT-1966 Авторизация с валидными данными")
    @Test
    void getSuccessLogoutAndLogin() {
        BasePage basePage = new BasePage();
        AuthPage authPage = new AuthPage();
        basePage.logout();
        Assertions.assertTrue(authPage.isOnAuthPage());

        AuthPage.autoVet();
        HomePage homePage = new HomePage();
        Assertions.assertTrue(homePage.isOnHomePage(), "Пользователь не авторизован");
        Assertions.assertTrue(homePage.getObjectAndAnimalAccordion(), "Список объектов и животных не отображается");
    }

    @DisplayName("RAT-1944 Просмотр карты объектов")
    @Test
    void viewMap() {
        Assertions.assertTrue(homePage.getMap(), "Карта не отображается");
        Assertions.assertTrue(homePage.closeMap(), "Кнопка 'Скрыть карту' не работает");
    }

    @DisplayName("RAT-1946 Перейти к реестру из карты")
    @Test
    void getEnterprisesRegistryPageFromMap() {
        homePage.getMap();
        Assertions.assertTrue(homePage.getEnterprisesRegistryPageFromMap(), "Кнопка 'Показать в реестре' не работает");
    }

    // Регистрация животного
    @DisplayName("RAT-1948 Регистрация животного")
    @Test
    void regIndividualAnimals() throws SQLException {
        AnimalFactory factory = new AnimalFactory();
        Animal krs = factory.createKRS();

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        registrationAnimalPage.getActivateRegistrationKRS(krs);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(krs.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(krs.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }

    @DisplayName("RAT-2767 Регистрация пчел")
    @Test
    void regBees() throws SQLException {
        AnimalFactory factory = new AnimalFactory();
        Animal bees = factory.createBees();

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        registrationAnimalPage.getActivateRegistrationBees(bees);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(bees.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(bees.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }

    @DisplayName("RAT-2768 Регистрация птиц")
    @Test
    void regChicken() throws SQLException {
        AnimalFactory factory = new AnimalFactory();
        Animal chicken = factory.createChicken();

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        registrationAnimalPage.getActivateRegistrationChickens(chicken);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(chicken.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(chicken.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }
    // Регистрация животного
    @DisplayName("RAT-1948 Регистрация группы животных (свиньи)")
    @Test
    void regAnimalGroup() throws SQLException {
        AnimalFactory factory = new AnimalFactory();
        AnimalGroup pigs = factory.createGroupOfPig();

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        registrationAnimalPage.getActivateRegistrationGroup(pigs);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(pigs.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(pigs.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }

//    @DisplayName("RAT-2669 Регистрация объекта," +
//            "RAT-2712 Редактирование объекта," +
//            "RAT-3290 Удаление объекта без животных ")
//    @Test
//    void regEnterprise(){
//        BasePage basePage = new BasePage();
//        basePage.getAddEnterprisePage();
//        AddEnterprisePage addEnterprisePage = new AddEnterprisePage();
//        String nameOfEnterprise = "Отомэйшн";
//        addEnterprisePage.getNewEnterprise(nameOfEnterprise, "Орловский", "Агеевка", "Богатая", "2", "2");
//        EnterpriseCardPage enterpriseCardPage = new EnterpriseCardPage();
//        Assertions.assertTrue(enterpriseCardPage.getNameValue().contains(nameOfEnterprise));
//
//        Assertions.assertTrue(dbHelper.isEnterpriseInDatabase(nameOfEnterprise));
//}
//    @DisplayName("Регистрация неверифицированного владельца")
//    @Test
//    void addNewOwner() {
//        BasePage basePage = new BasePage();
//        basePage.getAddEnterprisePage();
//        AddEnterprisePage addEnterprisePage = new AddEnterprisePage();
//
//        String inn = DataGenerator.getNumber(10);
//
//        addEnterprisePage.getNewOwnerLegalEntity(inn);
//
//        Assertions.assertEquals(inn, addEnterprisePage.getOwnersInn());
//
//    }
}

