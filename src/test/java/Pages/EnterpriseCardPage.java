package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EnterpriseCardPage {
    private SelenideElement heading = $x("//h2[text()='Карточка объекта']");
    private SelenideElement name = $x("//*[@id=show-enterprise-page]/div[4]/div/div[1]/div[1]");
    private SelenideElement owner = $x("//*[@id=v-pills-tabContent]/div[1]/div[1]/div[2]/div[2]");
    private SelenideElement inn = $x("//*[@id=v-pills-tabContent]/div[1]/div[2]/div[2]/div[2]");
    private SelenideElement kpp = $x("//*[@id=v-pills-tabContent]/div[1]/div[3]/div[3]/div[2]");
    private SelenideElement serviceArea = $x("//*[@id=v-pills-tabContent]/div[1]/div[4]/div[1]/div[2]");
    private SelenideElement fullAddress = $x("//*[@id=v-pills-tabContent]/div[1]/div[4]/div[2]/div[2]");
    private SelenideElement actionsButton = $x("//*[@id=dropdownEdit]");
    private SelenideElement actionsMenu = $x("//*[@id=show-enterprise-page]/div[4]/div/div[1]/div[2]/div/ul");
    private SelenideElement editOwnerButton = $x("//a[contains(text(),'Редактировать владельца')]");

    public EnterpriseCardPage(){
        heading.isDisplayed();
    }
    public String getInnValue() {
        return inn.getText();
    }

    public String getOwnerValue() {
        return owner.getText();
    }

    public String getNameValue() {
        return name.getText();
    }
    public String getServiceAreaValue() {
        return serviceArea.getText();
    }
    public String getFullAddressValue() {
        return fullAddress.getText();
    }

    public void editInnOfOwner(String newNumber){
        actionsButton.click();
        actionsMenu.isDisplayed();
        editOwnerButton.click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        ownerModalWindow.editInn(newNumber);
    }
}
