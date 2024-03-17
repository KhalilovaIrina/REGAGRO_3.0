package tests;

import Pages.AddEnterprisePage;
import Pages.AuthPage;
import Pages.BasePage;
import Pages.EnterpriseCardPage;
import Pages.EnterpriseList;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import dataGenerator.DataGenerator;
import entities.Enterprise;
import helpers.DBHelper;
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
public class RegistrationEditDeleteEnterpriseTest {
    static DataGenerator dataGenerator = new DataGenerator();
    private static final String name = dataGenerator.getEnterpriseName();
    private static String newName = dataGenerator.getEnterpriseName();

    @BeforeAll
    static void setUpAll() {
       // Configuration.headless = true;
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
    @DisplayName("RAT-2669 Регистрация объекта")
    @Test
    void regEnterprise() {
        DBHelper dbHelper = new DBHelper();
        BasePage basePage = new BasePage();
        basePage.getAddEnterprisePage();
        AddEnterprisePage addEnterprisePage = new AddEnterprisePage();
        Enterprise enterprise = new Enterprise();
        addEnterprisePage.getNewEnterprise(enterprise, name);
        Selenide.sleep(3000);
        EnterpriseCardPage enterpriseCardPage = new EnterpriseCardPage();

        Assertions.assertTrue(enterpriseCardPage.getNameOfEnterprise().contains(name));
        Assertions.assertTrue(dbHelper.isValueInDatabase("name", "enterprises", name));
    }

    @Order(2)
    @DisplayName("RAT-2712 Редактирование объекта")
    @Test
    void getSuccessEditEnterprise() {
        DBHelper dbHelper = new DBHelper();
        EnterpriseCardPage enterpriseCardPage = new EnterpriseCardPage();
        if (newName.matches(name)) {
            newName = dataGenerator.getEnterpriseName();
        }
        enterpriseCardPage.editEnterpriseName(newName);

        Assertions.assertTrue(dbHelper.isValueInDatabase("name", "enterprises", newName));
        //Assertions.assertFalse(dbHelper.isValueInDatabase("name", "enterprises", name));
    }

    @Order(3)
    @DisplayName("RAT-3290 Удаление объекта без животных")
    @Test
    void getSuccessDeleteEnterprise() {
        Selenide.sleep(3000);
        DBHelper dbHelper = new DBHelper();
        BasePage basePage = new BasePage();
        basePage.logout();
        AuthPage authPage = new AuthPage();
        authPage.authSuperAdmin();
        basePage.getEnterpriseList();
        EnterpriseList enterpriseList = new EnterpriseList();
        enterpriseList.getFindEnterprisePage(newName);
        Selenide.sleep(4000);
        EnterpriseCardPage enterpriseCardPage = new EnterpriseCardPage();
        enterpriseCardPage.deleteEnterprise();
        Assertions.assertTrue(dbHelper.isDeleted(newName, "enterprises"));
    }
}
