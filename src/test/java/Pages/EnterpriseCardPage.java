package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.DBHelper;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class EnterpriseCardPage {

    private SelenideElement heading = $x("//h2[text()='Карточка объекта']");

    private SelenideElement owner = $x("//*[@id=v-pills-tabContent]/div[1]/div[1]/div[2]/div[2]");
    private SelenideElement inn = $x("//*[@id=v-pills-tabContent]/div[1]/div[2]/div[2]/div[2]");
    private SelenideElement kpp = $x("//*[@id=v-pills-tabContent]/div[1]/div[3]/div[3]/div[2]");
    private SelenideElement serviceArea = $x("//*[@id=v-pills-tabContent]/div[1]/div[4]/div[1]/div[2]");
    private SelenideElement fullAddress = $x("//*[@id=v-pills-tabContent]/div[1]/div[4]/div[2]/div[2]");
    private SelenideElement actionsButton = $x("//span[contains(text(), 'Действия')]");
    private SelenideElement actionsMenu = $x("//ul[@class='dropdown-menu show']");
    private SelenideElement editOwnerButton = $x("//a[contains(text(),'Редактировать владельца')]");
    private SelenideElement editEnterpriseButton = $x("//a[contains(text(),'Редактировать объект')]");
    private SelenideElement deleteEnterpriseButton = $x("//a[contains(text(),'Удалить объект')]");
    private SelenideElement okButton = $x("//button[contains(text(),'Да')]");
    private SelenideElement messageDelete = $x("//h4[contains(text(),'Удалить объект')]");

    public EnterpriseCardPage() {
        heading.should(Condition.visible);
    }

    DBHelper dbHelper = new DBHelper();

    public String getInnValue() {
        return inn.getText();
    }

    public String getOwnerValue() {
        return owner.getText();
    }

    public String getNameValue(String enterpriseName) {
        String xpathLocatorName = String.format("//div[@class='col-auto'][contains(text(), '%s')]", enterpriseName);
        return $x(xpathLocatorName).getText();
    }

    public String getServiceAreaValue() {
        return serviceArea.getText();
    }

    public String getFullAddressValue() {
        return fullAddress.getText();
    }

    public void editInnOfOwner(String newNumber) {
        actionsButton.click();
        actionsMenu.isDisplayed();
        editOwnerButton.click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        ownerModalWindow.editInn(newNumber);
    }

    public EnterpriseCardPage editEnterpriseName(String newName) {
        actionsButton.click();
        actionsMenu.should(Condition.visible);
        editEnterpriseButton.click();
        EditEnterprisePage editEnterprisePage = new EditEnterprisePage();
        return editEnterprisePage.getEditEnterprise(newName);
    }

    public EnterpriseList deleteEnterprise() {
        Selenide.sleep(4000);
        actionsButton.click();
        actionsMenu.should(Condition.visible);
        deleteEnterpriseButton.click();
        messageDelete.should(Condition.visible);
        okButton.click();

        return new EnterpriseList();
    }
}
