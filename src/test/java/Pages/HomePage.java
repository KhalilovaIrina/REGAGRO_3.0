package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private static final String EXPECTED_URL = "https://v3.dev.regagro.ru/";
    public boolean isOnHomePage() {
        return WebDriverRunner.url().equals(EXPECTED_URL);
    }
    BasePage basePage = new BasePage();

    // Объекты и животные
    private SelenideElement objectAccordionButton = $x("//button[text()='Объекты']");
    private SelenideElement animalsAccordionButton = $x("//button[text()='Животные']");

    private SelenideElement objectAccordionButtonShow = $x("//*[@id=home-page]/div/div[4]/div/div");
    private SelenideElement animalsAccordionButtonShow = $x("//*[@id=home-page]/div/div[5]/div/div");

    // Фильтры
    private SelenideElement filtersButton = $x("//*[@id=home-page]/div/div[1]/div[1]/button");
    private SelenideElement filtersModalShow = $x("/*[@id=home-page]/div/div[2]/div");

    //$x("");

    private SelenideElement mapButton = $x("//button[text()='Показать карту']");
   // private SelenideElement yandexMap = $x("//ymaps[contains(@class, 'ymaps-2-1-79-map')][contains(@style, 'width: 1811px; height: 560px')]");
    private SelenideElement yandexMap =  $("ymaps[class=ymaps-2-1-79-map]");

    private SelenideElement hideMapButton = $x("//button[text()='Скрыть карту']");
    private SelenideElement showRegistryButton = $x("//button[text()='Показать в реестре']");

    public HomePage(){
       mapButton.shouldBe(visible, Duration.ofSeconds(10));
    }
    EnterprisesPage enterprisesPage = new EnterprisesPage();
    public boolean getMap() {
        mapButton.click();
        return yandexMap.isDisplayed();
    }

    public boolean closeMap() {
        hideMapButton.click();
        yandexMap.shouldNot(visible,Duration.ofSeconds(1));
        return true;
    }

    public boolean getEnterprisesRegistryPageFromMap() {
        showRegistryButton.click();
        return enterprisesPage.isOnEnterprisesPage();
    }

    public boolean getObjectAndAnimalAccordion(){
        Selenide.sleep(2000);
        objectAccordionButton.isDisplayed();
        animalsAccordionButton.isDisplayed();
        return true;
    }

}
