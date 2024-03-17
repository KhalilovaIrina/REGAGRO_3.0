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
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationEditDeleteEnterpriseTest {
    DataGenerator dataGenerator = new DataGenerator();
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
    @DisplayName("RAT-2669 Регистрация объекта," +
            "RAT-2712 Редактирование объекта," +
            "RAT-3290 Удаление объекта без животных ")
    @Test
    void regEnterprise() {
        DBHelper dbHelper = new DBHelper();
        BasePage basePage = new BasePage();
        basePage.getAddEnterprisePage();
        AddEnterprisePage addEnterprisePage = new AddEnterprisePage();
        Enterprise enterprise = new Enterprise();
        addEnterprisePage.getNewEnterprise(enterprise);
        Selenide.sleep(3000);
        EnterpriseCardPage enterpriseCardPage = new EnterpriseCardPage();
        Assertions.assertTrue(enterpriseCardPage.getNameValue(enterprise.getName()).contains(enterprise.getName()));

        Assertions.assertTrue(dbHelper.isEnterpriseInDatabase(enterprise.getName()));

        // Редактирование объекта
        String oldName = enterprise.getName();
        String newName = dataGenerator.getEnterpriseName();
        if (newName.matches(enterprise.getName())) {
            dataGenerator.getEnterpriseName();
        }
        enterpriseCardPage.editEnterpriseName(newName);
        Assertions.assertTrue(dbHelper.isEnterpriseInDatabase(newName));
        Assertions.assertFalse(dbHelper.isValueInDatabase("name", "enterprises", oldName));

        // Удаление объекта
        Selenide.sleep(3000);
        basePage.logout();
        AuthPage authPage = new AuthPage();
        authPage.authSuperAdmin();
        basePage.getEnterpriseList();
        EnterpriseList enterpriseList = new EnterpriseList();
        enterpriseList.getFindEnterprisePage(newName);
        Selenide.sleep(4000);
        enterpriseCardPage.deleteEnterprise();
        Assertions.assertTrue(dbHelper.isDeleted(newName));
    }
}
