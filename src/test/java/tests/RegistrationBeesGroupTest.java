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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationBeesGroupTest {
    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://v3.dev.regagro.ru/");
        AuthPage.autoVet();
        //Configuration.holdBrowserOpen = true;
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
    @DisplayName("RAT-919 Регистрация пасеки")
    @Test
    void regAnimalGroupBees() {
        DBHelper dbHelper = new DBHelper();
        AnimalGroup bees = AnimalGroup.createAnimalGroup("Пчёлы");

        BasePage basePage = new BasePage();
        basePage.getAddAnimalGroupPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        assert bees != null;
        registrationAnimalPage.getActivateRegistrationBees(bees);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalGroupPassportPage();
        AnimalGroupPassportPage animalGroupPassportPage = new AnimalGroupPassportPage();
        Assertions.assertEquals(bees.getIdentificationNumber(), animalGroupPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(bees.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }
}
