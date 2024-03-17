package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$x;

public class EnterpriseList {
    private final SelenideElement filtersButton = $x("//button[contains(text(), 'Фильтры')]");
    private final SelenideElement generalValue = $x("//button[contains(text(), 'Общие')]");
    private final SelenideElement enterpriseName = $x("//*[@id='enterprises-page']/div/div[6]/div/div/div[2]/div[7]/div/div/div/div[2]/div/input");
    private final SelenideElement okButton = $x("//button[@id='applyFilterBtn']");
    private final SelenideElement enterpriseNameInList = $x("//div[contains(text(), 'Буль')]");
    private static final String EXPECTED_URL = "https://v3.dev.regagro.ru/enterprises?";


    public EnterpriseList() {
        {
            Selenide.sleep(2500);
            SelenideElement heading = Selenide.$x("//h2[text()='Реестр объектов']");
            heading.should(Condition.visible);
        }
    }
    public boolean isOnEnterprisesListPage() {
        return WebDriverRunner.url().equals(EXPECTED_URL);
    }

    public EnterpriseCardPage getFindEnterprisePage(String name){
        filtersButton.should(Condition.enabled).click();
        generalValue.should(Condition.enabled).click();
        enterpriseName.setValue(name).pressEnter();
        okButton.should(Condition.enabled).click();
        enterpriseNameInList.should(Condition.enabled).click();
        return new EnterpriseCardPage();
    }
}
