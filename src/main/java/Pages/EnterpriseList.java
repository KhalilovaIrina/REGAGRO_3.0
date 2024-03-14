package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EnterpriseList {
    private SelenideElement heading = Selenide.$x("//h2[text()='Реестр объектов']");
    private SelenideElement filtresButton = Selenide.$x("//button[contains(text(), 'Фильтры')]");
    private SelenideElement generalValue = Selenide.$x("//button[contains(text(), 'Общие')]");
    private SelenideElement enterpriseName = Selenide.$x("//*[@id='enterprises-page']/div/div[6]/div/div/div[2]/div[7]/div/div/div/div[2]/div/input");
    private SelenideElement okButton = Selenide.$x("//button[@id='applyFilterBtn']");
    private SelenideElement enterpriseNameInList = Selenide.$x("//div[contains(text(), 'Буль')]");



    public EnterpriseList() {
        {
            heading.should(Condition.visible);
        }
    }

    public EnterpriseCardPage getFindEnterprisePage(String name){
        filtresButton.click();
        generalValue.click();
        enterpriseName.setValue(name).pressEnter();
        okButton.click();
        enterpriseNameInList.click();
        return new EnterpriseCardPage();
    }


}
