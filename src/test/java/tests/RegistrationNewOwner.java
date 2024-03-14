package tests;

import Pages.AuthPage;
import Pages.BasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationNewOwner {
//    @BeforeAll
//    static void setUpAll() {
//        Configuration.headless = true;
//        SelenideLogger.addListener("allure", new AllureSelenide());
//        open("https://v3.dev.regagro.ru/");
//        AuthPage.autoVet();
//        //Configuration.holdBrowserOpen = true;
//    }
//
//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//        closeWebDriver();
//    }
//
//    @AfterEach
//    public void getHomePage() {
//        BasePage basePage = new BasePage();
//        basePage.getHomePage();
//    }
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
