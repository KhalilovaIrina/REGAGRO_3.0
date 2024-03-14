package tests;

import Pages.AuthPage;
import Pages.BasePage;
import Pages.HomePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ToRegistryFromMapTest {
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
    @DisplayName("RAT-1946 Перейти к реестру из карты")
    @Test
    void getEnterprisesRegistryPageFromMap() {
        HomePage homePage = new HomePage();
        homePage.getMap();
        Assertions.assertTrue(homePage.getEnterprisesRegistryPageFromMap(), "Кнопка 'Показать в реестре' не работает");
    }
}
