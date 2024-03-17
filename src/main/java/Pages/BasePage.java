package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    private final SelenideElement user = $x("/html/body/nav/div/div[2]/ul[2]/li[2]/a");
    private final SelenideElement logout = $x("/html/body/nav/div/div[2]/ul[2]/li[2]/div/div");
    // Сайдбар
    private final SelenideElement mapAccordionButton = $x("/html/body/div[2]/div/div[1]/div/div[1]/a");

    //Регистрация
    private final SelenideElement registrationAccordionButton = $("#accordionMenu > div:nth-child(2)");
    //$x("//button[text()='Регистрация']");
    private final SelenideElement objectRegistrationAccordionButton = $x("//div[contains(text(),'Объект')]");
    private final SelenideElement animalRegistrationAccordionButton = $x("//div[contains(text(),'Животное')]");
    private final SelenideElement animalGroupRegistrationAccordionButton = $x("//div[contains(text(), 'Группа животных')]");

    // Реестры
    private final SelenideElement registryAccordionButton = $x("//*[@id='flush-headingTwo']/button");
    private final SelenideElement enterprisesAccordionButton = $x("//a[@href='https://v3.dev.regagro.ru/enterprises']");
    private final SelenideElement findAnimalField = $x("//span[@title='Поиск животного (рег. номер)']");
    private final SelenideElement input = $x("//input[@class='select2-search__field']");
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
        registrationAccordionButton.should(Condition.enabled).click();
        objectRegistrationAccordionButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
        return new AddEnterprisePage();
    }

// Переход из сайдбара на страницу Регистрации животного

    public AddAnimalPage getAddAnimalPage() {
        registrationAccordionButton.should(Condition.enabled).click();
        animalRegistrationAccordionButton.should(Condition.enabled).click();
        return new AddAnimalPage();
    }

    public AddAnimalPage getAddAnimalGroupPage() {
        registrationAccordionButton.should(Condition.enabled).click();
        animalGroupRegistrationAccordionButton.should(Condition.enabled).click();
        return new AddAnimalPage();
    }

    public HomePage getHomePage() {
        mapAccordionButton.should(Condition.enabled).click();
        return new HomePage();
    }

    // Переход из сайдбара на страницу Реестр объектов
    public EnterpriseList getEnterpriseList() {
        registryAccordionButton.should(Condition.enabled).click();

        enterprisesAccordionButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
        return new EnterpriseList();
    }

    // Поиск индивидуального животного
    public AnimalPassportPage getFoundAnimal(String number) {
        findAnimalField.should(Condition.enabled).click();
        input.setValue(number);
        Selenide.sleep(2000);
        input.pressEnter();
        return new AnimalPassportPage();
    }

    public AnimalGroupPassportPage getFoundAnimalGroup(String number) {
        findAnimalField.should(Condition.enabled).click();
        input.setValue(number);
        Selenide.sleep(2000);
        input.pressEnter();
        return new AnimalGroupPassportPage();
    }

}
