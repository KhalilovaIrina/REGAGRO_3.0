package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EnterpriseCardPage {
    private final SelenideElement actionsButton = $x("//span[contains(text(), 'Действия')]");
    private final SelenideElement actionsMenu = $x("//ul[@class='dropdown-menu show']");
    private final SelenideElement editOwnerButton = $x("//a[contains(text(),'Редактировать владельца')]");
    private final SelenideElement editEnterpriseButton = $x("//a[contains(text(),'Редактировать объект')]");
    private final SelenideElement deleteEnterpriseButton = $x("//a[contains(text(),'Удалить объект')]");
    private final SelenideElement okButton = $x("//button[contains(text(),'Да')]");
    private final SelenideElement messageDelete = $x("//h4[contains(text(),'Удалить объект')]");
    private final SelenideElement nameOfEnterprise = $x("//div[@class='row justify-content-between']/child::div[1]");

    public EnterpriseCardPage() {
        Selenide.sleep(3000);
        SelenideElement heading = Selenide.$x("//h2[text()='Карточка объекта']");
        heading.should(Condition.visible);
    }

    public String getNameValue(String enterpriseName) {
        String xpathLocatorName = String.format("//div[@class='col-auto'][contains(text(), '%s')]", enterpriseName);
        return Selenide.$x(xpathLocatorName).getText();
    }
    public String getNameOfEnterprise() {
        return nameOfEnterprise.getText();
    }
    public void editInnOfOwner(String newNumber) {
        actionsButton.should(Condition.enabled).click();
        actionsMenu.should(Condition.visible);
        editOwnerButton.should(Condition.enabled).click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        ownerModalWindow.editInn(newNumber);
    }

    public EnterpriseCardPage editEnterpriseName(String newName) {
        actionsButton.should(Condition.enabled).click();
        actionsMenu.should(Condition.visible);
        editEnterpriseButton.should(Condition.enabled).click();
        EditEnterprisePage editEnterprisePage = new EditEnterprisePage();
        return editEnterprisePage.getEditEnterprise(newName);
    }

    public EnterpriseList deleteEnterprise() {
        Selenide.sleep(4000);
        actionsButton.should(Condition.enabled).click();
        actionsMenu.should(Condition.visible);
        deleteEnterpriseButton.should(Condition.enabled).click();
        messageDelete.should(Condition.visible);
        okButton.should(Condition.enabled).click();
        return new EnterpriseList();
    }
}
