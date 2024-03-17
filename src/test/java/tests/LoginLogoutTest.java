package tests;

import Pages.AuthPage;
import Pages.BasePage;
import Pages.HomePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class LoginLogoutTest {
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
    @DisplayName("RAT-1964 Выход из профиля пользователя и RAT-1966 Авторизация с валидными данными")
    @Test
    void getSuccessLogoutAndLogin() {
        BasePage basePage = new BasePage();
        basePage.logout();
        AuthPage authPage = new AuthPage();
        Assertions.assertTrue(authPage.isOnAuthPage());

        authPage.autoVet();
        HomePage homePage = new HomePage();
        Assertions.assertTrue(homePage.isOnHomePage(), "Пользователь не авторизован");
        Assertions.assertTrue(homePage.getObjectAndAnimalAccordion(), "Список объектов и животных не отображается");
    }
}
