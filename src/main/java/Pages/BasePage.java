package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.DBHelper;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    private SelenideElement user = $x("/html/body/nav/div/div[2]/ul[2]/li[2]/a");
    private SelenideElement logout = $x("/html/body/nav/div/div[2]/ul[2]/li[2]/div/div");
    // Сайдбар
    private SelenideElement sideBar = $x("//*[@id=sidebar]");
    private SelenideElement hideSideBar = $x("//button[text()='Свернуть']");
    private SelenideElement mapAccordionButton = $x("/html/body/div[2]/div/div[1]/div/div[1]/a");

    //Регистрация
    private SelenideElement registrationAccordionButton = $("#accordionMenu > div:nth-child(2)");
    //$x("//button[text()='Регистрация']");
    private SelenideElement objectRegistrationAccordionButton = $x("//div[contains(text(),'Объект')]");
    private SelenideElement animalRegistrationAccordionButton = $x("//div[contains(text(),'Животное')]");
    private SelenideElement animalGroupRegistrationAccordionButton = $x("//div[contains(text(), 'Группа животных')]");

    // Реестры
    private SelenideElement registryAccordionButton = $x("//*[@id='flush-headingTwo']/button");
    private SelenideElement enterprisesAccordionButton = $x("//a[@href='https://v3.dev.regagro.ru/enterprises']");
    private SelenideElement findAnimalField = $x("//span[@title='Поиск животного (рег. номер)']");
    private SelenideElement input = $x("//input[@class='select2-search__field']");
    // Выбытие
    // Задания
    // Отчеты и аналитика
    //Справочники

    public AuthPage logout() {
        user.click();
        logout.click();
        return new AuthPage();
    }

    // Переход из сайдбара на страницу Регистрации объекта
    public AddEnterprisePage getAddEnterprisePage() {
        registrationAccordionButton.click();
        objectRegistrationAccordionButton.click();
        Selenide.sleep(2000);
        return new AddEnterprisePage();
    }

// Переход из сайдбара на страницу Регистрации животного

    public AddAnimalPage getAddAnimalPage() {
        registrationAccordionButton.click();
        animalRegistrationAccordionButton.click();
        return new AddAnimalPage();
    }

    public AddAnimalPage getAddAnimalGroupPage() {
        registrationAccordionButton.click();
        animalGroupRegistrationAccordionButton.click();
        return new AddAnimalPage();
    }

    public HomePage getHomePage() {
        mapAccordionButton.click();
        return new HomePage();
    }

    // Переход из сайдбара на страницу Реестр объектов
    public EnterpriseList getEnterpriseList() {
        registryAccordionButton.click();

        enterprisesAccordionButton.click();
        Selenide.sleep(2000);
        return new EnterpriseList();
    }

    // Поиск индивидуального животного
    public AnimalPassportPage getFoundAnimal(String number) {
        findAnimalField.click();
        input.setValue(number);
        Selenide.sleep(2000);
        input.pressEnter();
        return new AnimalPassportPage();
    }

    public AnimalGroupPassportPage getFoundAnimalGroup(String number) {
        findAnimalField.click();
        input.setValue(number);
        Selenide.sleep(2000);
        input.pressEnter();
        return new AnimalGroupPassportPage();
    }

}
