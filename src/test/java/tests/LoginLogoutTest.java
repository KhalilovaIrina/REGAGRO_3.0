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

public class LoginLogoutTest {

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

//    @AfterEach
//    public void getHomePage() {
//        BasePage basePage = new BasePage();
//        basePage.getHomePage();
//    }
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
}
