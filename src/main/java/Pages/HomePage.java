package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class HomePage {

    // Объекты и животные
    private final SelenideElement objectAccordionButton = $x("//button[contains(text(),'Объекты')]");
    private final SelenideElement animalsAccordionButton = $x("//button[contains(text(),'Животные')]");
    private final SelenideElement mapButton = $x("//button[contains(text(),'Показать карту')]");
    private final SelenideElement yandexMap = $("ymaps[class=ymaps-2-1-79-map]");
    private final SelenideElement hideMapButton = $x("//button[contains(text(),'Скрыть карту')]");
    private final SelenideElement showRegistryButton = $x("//button[contains(text(),'Показать в реестре')]");
    private static final String EXPECTED_URL = "https://v3.dev.regagro.ru/";

    public boolean isOnHomePage() {
        return WebDriverRunner.url().equals(EXPECTED_URL);
    }

    public HomePage() {
        mapButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public boolean getMap() {
        mapButton.should(Condition.enabled).click();
        Selenide.sleep(2000);
        yandexMap.should(Condition.visible, Duration.ofSeconds(1));
        return true;
    }

    public boolean closeMap() {
        hideMapButton.should(Condition.enabled).click();
        yandexMap.shouldNot(Condition.visible, Duration.ofSeconds(2));
        return true;
    }

    public boolean getEnterprisesRegistryPageFromMap() {
        showRegistryButton.click();
        EnterpriseList enterpriseList = new EnterpriseList();
        return enterpriseList.isOnEnterprisesListPage();
    }

    public boolean getObjectAndAnimalAccordion() {
        Selenide.sleep(2000);
        objectAccordionButton.should(Condition.visible, Duration.ofSeconds(3));
        animalsAccordionButton.should(Condition.visible, Duration.ofSeconds(3));
        return true;
    }
}
