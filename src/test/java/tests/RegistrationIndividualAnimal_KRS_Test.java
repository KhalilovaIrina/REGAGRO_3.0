package tests;

import Pages.AddAnimalPage;
import Pages.AnimalPassportPage;
import Pages.AuthPage;
import Pages.BasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import entities.Animal;
import helpers.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationIndividualAnimal_KRS_Test {
    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://v3.dev.regagro.ru/");
        AuthPage authPage = new AuthPage();
        authPage.autoVet();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }
    @DisplayName("RAT-1948 Регистрация животного")
    @Test
    void regIndividualAnimals() {
        DBHelper dbHelper = new DBHelper();
        Animal krs = Animal.createAnimal("Крупный рогатый скот");

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        assert krs != null;
        registrationAnimalPage.getActivateRegistrationKRS(krs);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(krs.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isValueInDatabase("number", "animals", krs.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }
}
