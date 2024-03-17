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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Order(1)
    @DisplayName("RAT-1964 Выход из профиля пользователя")
    @Test
    void getSuccessLogout() {
        BasePage basePage = new BasePage();
        basePage.logout();
        AuthPage authPage = new AuthPage();
        Assertions.assertTrue(authPage.isOnAuthPage());
    }

    @Order(2)
    @DisplayName("RAT-1966 Авторизация с валидными данными")
    @Test
    void getSuccessLogin() {
        AuthPage authPage = new AuthPage();
        authPage.autoVet();
        HomePage homePage = new HomePage();
        Assertions.assertTrue(homePage.isOnHomePage(), "Пользователь не авторизован");
        Assertions.assertTrue(homePage.getObjectAndAnimalAccordion(), "Список объектов и животных не отображается");
    }
}
