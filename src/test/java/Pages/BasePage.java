package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private SelenideElement user =$x("/html/body/nav/div/div[2]/ul[2]/li[2]/a");
    private SelenideElement logout =$x("/html/body/nav/div/div[2]/ul[2]/li[2]/div/div");
    // Сайдбар
    private SelenideElement sideBar = $x("//*[@id=sidebar]");
    private SelenideElement hideSideBar = $x("//button[text()='Свернуть']");
    private SelenideElement mapAccordionButton = $x("/html/body/div[2]/div/div[1]/div/div[1]/a");

    //Регистрация
    private SelenideElement registrationAccordionButton = $("#accordionMenu > div:nth-child(2)");
    //$x("//button[text()='Регистрация']");
    private SelenideElement objectRegistrationAccordionButton = $x("//div[contains(text(),'Объект')]");
    private SelenideElement animalRegistrationAccordionButton = $x("//div[contains(text(),'Животное')]");
    private SelenideElement animalGroupRegistrationAccordionButton = $x("//div[contains(text(),'Группа животных')]");

    // Реестры
    private SelenideElement registryAccordionButton = $x("//*[@id='flush-headingTwo']/button");
    private SelenideElement enterprisesAccordionButton = $x("//a[@href='https://v3.dev.regagro.ru/enterprises']");
    private SelenideElement terminatedAnimalsAccordionButton = $x("//button[text()='Выбывшие животные']");
    private SelenideElement animalAccordionButton = $x("//button[text()='Животное']");
    private SelenideElement disposalListAccordionButton = $x("//button[text()='Выбытие']");
    private SelenideElement inventoriesAccordionButton = $x("//button[text()='Опись']");
    private SelenideElement vaccinationsAccordionButton = $x("//button[text()='Мероприятие']");
    private SelenideElement ownersAccordionButton = $x("//button[text()='Владелец']");
    private SelenideElement applicationsAccordionButton = $x("//button[text()='Заявка']");

    // Выбытие
    private SelenideElement disposalAccordionButton = $x("//button[text()='Выбытие...']");
    private SelenideElement disposalIntoAccordionButton = $x("//button[text()='Выбытие']");

    // Задания
    private SelenideElement tasksAccordionButton = $x("//button[text()='Задания']");
    private SelenideElement tasksListAccordionButton = $x("//button[text()='Список заданий']");

    // Отчеты и аналитика
    private SelenideElement reportAnalyticAccordionButton = $x("//button[text()='Отчеты и аналитика']");
    private SelenideElement analyticAccordionButton = $x("//button[text()='Аналитика']");
    //Справочники
    private SelenideElement handbookAccordionButton = $x("//button[contains(text(), 'Справочник') and contains(@class, 'collapsed')]");


    private SelenideElement helpButton = $x("//button[text()='Помощь']");


    public AuthPage logout(){
        user.click();
        logout.click();
        return new AuthPage();
    }
    @DisplayName("Переход из сайдбара на страницу Регистрации объекта")
    public AddEnterprisePage getAddEnterprisePage(){
        registrationAccordionButton.click();
        objectRegistrationAccordionButton.click();
        Selenide.sleep(2000);
        return new AddEnterprisePage();
    }

    @DisplayName("Переход из сайдбара на страницу Регистрации животного")

    public AddAnimalPage getAddAnimalPage() {
        registrationAccordionButton.click();
        animalRegistrationAccordionButton.click();
        return new AddAnimalPage();
    }
    public HomePage getHomePage() {
        mapAccordionButton.click();
        return new HomePage();
    }

    @DisplayName("Переход из сайдбара на страницу Реестр объектов")
    public EnterpriseList getEnterpriseList(){
        registryAccordionButton.click();

        enterprisesAccordionButton.click();
        Selenide.sleep(2000);
        return new EnterpriseList();
    }
}
