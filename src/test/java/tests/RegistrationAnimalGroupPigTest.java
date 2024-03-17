package tests;

import Pages.AddAnimalPage;
import Pages.AnimalGroupPassportPage;
import Pages.AuthPage;
import Pages.BasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import entities.AnimalGroup;
import helpers.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationAnimalGroupPigTest {
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
    @DisplayName("RAT-1948 Регистрация группы животных (свиньи)")
    @Test
    void regAnimalGroup() {
        DBHelper dbHelper = new DBHelper();
        AnimalGroup pigs = AnimalGroup.createAnimalGroup("Свиньи");

        BasePage basePage = new BasePage();
        basePage.getAddAnimalGroupPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        assert pigs != null;
        registrationAnimalPage.getActivateRegistrationGroup(pigs);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalGroupPassportPage();
        AnimalGroupPassportPage animalGroupPassportPage = new AnimalGroupPassportPage();
        Assertions.assertEquals(pigs.getIdentificationNumber(), animalGroupPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isValueInDatabase("number", "animals", pigs.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }
}
