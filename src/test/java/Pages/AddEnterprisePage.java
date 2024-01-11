package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;

import static com.codeborne.selenide.Selenide.$x;

public class AddEnterprisePage {
    private SelenideElement heading = $x("//h2[text()='Регистрация поднадзорного объекта']");
    private SelenideElement chooseOwnerButton = $x("//button[contains(text(),'Выбрать владельца')]");
    private SelenideElement modalWindowFindOwner = $x("//h4[contains(text(),'Поиск владельца в системе Regagro')]");
    private SelenideElement innField = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/form/div/div/div/div[2]/div/div[3]/div[1]/input");
    private SelenideElement findButton = $x("//button[contains(text(),'Найти')]");
    private SelenideElement registrationOwnerFromCerber = $x("//button[contains(text(),'Регистрация владельца из Цербер')]");
    private SelenideElement registrationOwner = $x("//button[contains(text(),'Регистрация неверифицированного владельца')]");
    private SelenideElement closeButton = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/form/div/div/div/div[1]/button");
    private SelenideElement backToLookOutButton = $x("//button[contains(text(),'Вернуться к поиску')]");
    private SelenideElement findItem = $x("//*[@id=search-results]/div/a/div[1]");
    private SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");
    private SelenideElement infoAboutEnterprise = $x("//button[contains(text(),'Информация об объекте')]");
    private SelenideElement nameOfEnterpriseField = $x("//*[@id=addEnterpriseForm]/div[1]/div[2]/textarea");
    private SelenideElement typeOfEnterprise = $x("//*[@id=addEnterpriseForm]/div[2]/div[2]/div/span/span[1]/span");
    private SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private SelenideElement districtSelection = $x("//*[@id=addEnterpriseForm ]/div[5]/div[2]/div/span/span[1]/span");
    private SelenideElement citySelection = $x("//*[@id=addEnterpriseForm]/div[6]/div[2]/div/span/span[1]/span");
    private SelenideElement serviceAreaSelection = $x("//*[@id=addEnterpriseForm]/div[12]/div[2]/div/span/span[1]/span");
    private SelenideElement activateRegistrationButton = $x("//button[contains(text(),'Завершить регистрацию')]");
    private SelenideElement innOwnersCard = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[5]/div[1]/div[2]/div[2]/div[2]");

    public AddEnterprisePage() {
        heading.isDisplayed();
    }
    public String getOwnersInn() {
        return innOwnersCard.getText();
    }
    public void getNewOwnerLegalEntity(String inn) {
        chooseOwnerButton.click();
        innField.setValue("1");
        findButton.click();
        registrationOwnerFromCerber.click();
        registrationOwner.click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow(registrationOwner);
        ownerModalWindow.getNewOwnerLegalEntity(inn);
//        backToLookOutButton.click();
//        innField.doubleClick();
//        innField.setValue(ownerModalWindow.getInnNewOwner());
//        findButton.click();
//        findItem.click();
        closeButton.click();
    }

    public EnterpriseCardPage getNewEnterprise
            (String inn, String nameOwner, String nameOfEnterprise, String district, String city, String serviceArea) {
        getNewOwnerLegalEntity(inn);
        infoAboutEnterprise.click();
        nameOfEnterpriseField.setValue(nameOfEnterprise);
        typeOfEnterprise.click();
        input.setValue("Птицеферма");
        districtSelection.click();
        input.setValue(district);
        citySelection.click();
        input.setValue(city);
        serviceAreaSelection.click();
        input.setValue(serviceArea);
        activateRegistrationButton.click();
        return new EnterpriseCardPage();
    }

}
