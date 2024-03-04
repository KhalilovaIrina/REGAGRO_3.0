package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dataGenerator.DataGenerator;
import entities.Enterprise;

import static com.codeborne.selenide.Selenide.$;
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
    private SelenideElement nameOfEnterpriseField = $x("//textarea[@name='name']");
    private SelenideElement typeOfEnterprise = $("#addEnterpriseForm > div:nth-child(2) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span");
    private SelenideElement typeresult = $("#select2--results > li:nth-child(5) > ul > li:nth-child(1)");
    private SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private SelenideElement districtSelection = $x("//*[@id='addEnterpriseForm']/div[5]/div[2]/div/span/span[1]/span");
    private SelenideElement citySelection = $x("//*[@id='addEnterpriseForm']/div[6]/div[2]/div/span/span[1]/span");
    private SelenideElement streetSelection = $x("//*[@id='addEnterpriseForm']/div[7]/div[2]/div/span/span[1]/span");
    private SelenideElement houseNumberDiv = $x("//div[contains(text(),'Дом')]/..//div[@class='col-6 mb-3 form-group pb-3']//input[@class='form-control']");
    private SelenideElement houseNumber = $x("//div[contains(text(),'Дом')]/..//input");
    //  private SelenideElement serviceAreaSelection = $("#service_area_id");
    private SelenideElement serviceAreaSelection = $x("//*[@id='addEnterpriseForm']/div[12]/div[2]/div/span/span[1]/span");

    private SelenideElement activateRegistrationButton = $("#submitFormsBtn");
    private SelenideElement innOwnersCard = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[5]/div[1]/div[2]/div[2]/div[2]");
    private SelenideElement saveButton = $x("//button[@id='submitFormsBtn']");
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
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        if (ownerModalWindow.isOwnerNotRegistered()) {
            ownerModalWindow.getNewOwnerLegalEntity(inn);
        }
//        backToLookOutButton.click();
//        innField.doubleClick();
//        innField.setValue(ownerModalWindow.getInnNewOwner());
//        findButton.click();
//        findItem.click();
        closeButton.click();
    }

    public EnterpriseCardPage getNewEnterpriseWithNewOwner
            (String inn, String nameOwner, String nameOfEnterprise, String district, String city, String serviceArea) {
        getNewOwnerLegalEntity(inn);
        //infoAboutEnterprise.click();
        nameOfEnterpriseField.setValue(nameOfEnterprise);
        typeOfEnterprise.click();
        typeresult.click();
        input.pressEnter();
        typeOfEnterprise.pressEnter();
        districtSelection.click();
        input.setValue(district).pressEnter();
        citySelection.click();
        input.setValue(city).pressEnter();
        serviceAreaSelection.click();
        input.setValue(serviceArea).pressEnter();
        activateRegistrationButton.click();
        return new EnterpriseCardPage();
    }

    public EnterpriseCardPage getNewEnterprise
            (Enterprise enterprise) {
        chooseOwnerButton.click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        ownerModalWindow.getOwner(enterprise);
        nameOfEnterpriseField.setValue(enterprise.getName());
        typeOfEnterprise.click();
        input.setValue(enterprise.getTypeOfEnterprise()).pressEnter();
        districtSelection.click();
        input.setValue(enterprise.getDistrict()).pressEnter();
        citySelection.click();
        input.setValue(enterprise.getCity()).pressEnter();
        Selenide.sleep(1000);
        if (enterprise.getStreet() != null) {
            streetSelection.click();
            input.setValue(enterprise.getStreet()).pressEnter();
        }
        houseNumberDiv.setValue(enterprise.getHouse()).pressEnter();
        //houseNumber.setValue(enterprise.getHouse()).pressEnter();
        serviceAreaSelection.click();
        input.setValue(enterprise.getServiceArea()).pressEnter();
        activateRegistrationButton.click();

        Selenide.sleep(2500);

        return new EnterpriseCardPage();
    }


    public EnterpriseCardPage getEditEnterprise(String newNameOfEnterprise){
        Selenide.sleep(5000);
        nameOfEnterpriseField.clear();
        nameOfEnterpriseField.setValue(newNameOfEnterprise);
        Selenide.sleep(3000);
        saveButton.click();
        Selenide.sleep(3000);
        return new EnterpriseCardPage();
    }
}