package tests;

import Pages.AnimalGroupPassportPage;
import Pages.AnimalPassportPage;
import Pages.AuthPage;
import Pages.BasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SearchAnimalByNumberTest {
    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://v3.dev.regagro.ru/");
        AuthPage authPage = new AuthPage();
        authPage.authSuperAdmin();
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }
    @DisplayName("RAT-2691 Поиск животного по номеру")
    @Test
    void getSuccessAnimalSearchByNumber(){
        BasePage basePage = new BasePage();
        DBHelper dbHelper = new DBHelper();

        String randomAnimalNumber = dbHelper.getRandomAnimalNumberFromDB();
        AnimalPassportPage animalPassportPage = basePage.getFoundAnimal(randomAnimalNumber);
        Assertions.assertEquals(animalPassportPage.getIdentificationNumber(), randomAnimalNumber);

        String randomAnimalGroupNumber = dbHelper.getRandomAnimalGroupNumberFromDB();
        AnimalGroupPassportPage animalGroupPassportPage = basePage.getFoundAnimalGroup(randomAnimalGroupNumber);
        Assertions.assertEquals(animalGroupPassportPage.getIdentificationNumber(), randomAnimalGroupNumber);
    }

}
