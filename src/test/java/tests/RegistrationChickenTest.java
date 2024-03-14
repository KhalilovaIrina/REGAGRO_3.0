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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationChickenTest {
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
    @DisplayName("RAT-2768 Регистрация птиц")
    @Test
    void regChicken() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        Animal chicken = Animal.createAnimal("Куры");

        BasePage basePage = new BasePage();
        basePage.getAddAnimalPage();

        AddAnimalPage registrationAnimalPage = new AddAnimalPage();
        assert chicken != null;
        registrationAnimalPage.getActivateRegistrationChickens(chicken);
        Assertions.assertTrue(registrationAnimalPage.getMessageSuccessRegistration());

        registrationAnimalPage.getAnimalPassportPage();
        AnimalPassportPage animalPassportPage = new AnimalPassportPage();
        Assertions.assertEquals(chicken.getIdentificationNumber(), animalPassportPage.getIdentificationNumber(),
                "Идентификационный номер, указанный при регистрации, не совпадает с номером в паспорте животного");

        Assertions.assertTrue(dbHelper.isAnimalInDatabase(chicken.getIdentificationNumber()),
                "Животное отсутствует в базе данных");
    }
}
