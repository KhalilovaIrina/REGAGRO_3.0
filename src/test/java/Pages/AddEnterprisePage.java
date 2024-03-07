package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import entities.Enterprise;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddEnterprisePage {
    private final SelenideElement heading = $x("//h2[text()='Регистрация поднадзорного объекта']");
    private final SelenideElement chooseOwnerButton = $x("//button[contains(text(),'Выбрать владельца')]");
    private final SelenideElement modalWindowFindOwner = $x("//h4[contains(text(),'Поиск владельца в системе Regagro')]");
    private final SelenideElement innField = $x("//small[contains(text(), 'ИНН')]/following-sibling::input");
    private final SelenideElement findButton = $x("//button[contains(text(),'Найти')]");
    private final SelenideElement registrationOwnerFromCerber = $x("//button[contains(text(),'Регистрация владельца из Цербер')]");
    private final SelenideElement registrationOwner = $x("//button[contains(text(),'Регистрация неверифицированного владельца')]");
    private final SelenideElement closeButton = $x("//h4[contains(text(), 'Поиск владельца')]/following-sibling::button");
    private final SelenideElement backToLookOutButton = $x("//button[contains(text(),'Вернуться к поиску')]");
    private final SelenideElement findItem = $x("//a[@class='search-results-item']");
    private final SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");
    private final SelenideElement infoAboutEnterprise = $x("//button[contains(text(),'Информация об объекте')]");
    private final SelenideElement nameOfEnterpriseField = $x("//textarea[@name='name']");
    private final SelenideElement typeOfEnterprise = $x("//select[@name='enterprise_type_id']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement typeResult = $("#select2--results > li:nth-child(5) > ul > li:nth-child(1)");
    private final SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private final SelenideElement districtSelection = $x("//select[@name='district_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement citySelection = $x("//select[@name='locality_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement streetSelection = $x("//select[@name='street_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement houseNumberDiv = $x("//div[contains(text(),'Дом')]/..//div[@class='col-6 mb-3 form-group pb-3']//input[@class='form-control']");
    private final SelenideElement houseNumber = $x("//div[contains(text(),'Дом')]/..//input");
    //  private SelenideElement serviceAreaSelection = $("#service_area_id");
    private final SelenideElement serviceAreaSelection = $x("//*[@id='addEnterpriseForm']/div[12]/div[2]/div/span/span[1]/span");

    private final SelenideElement activateRegistrationButton = $("#submitFormsBtn");
    private final SelenideElement innOwnersCard = $x("//div[contains(text(),'ИНН')]/following-sibling::div[@data-v-1186336b]");
    private final SelenideElement saveButton = $x("//button[@id='submitFormsBtn']");
    public AddEnterprisePage() {
        Selenide.sleep(2000);
        heading.should(Condition.visible);
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
        typeResult.click();
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
        Selenide.sleep(2000);
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