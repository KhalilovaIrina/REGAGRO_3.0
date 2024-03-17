package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import entities.Enterprise;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddEnterprisePage {
    private final SelenideElement chooseOwnerButton = $x("//button[contains(text(),'Выбрать владельца')]");private final SelenideElement innField = Selenide.$x("//small[contains(text(), 'ИНН')]/following-sibling::input");
    private final SelenideElement findButton = $x("//button[contains(text(),'Найти')]");
    private final SelenideElement registrationOwnerFromCerber = $x("//button[contains(text(),'Регистрация владельца из Цербер')]");
    private final SelenideElement registrationOwner = $x("//button[contains(text(),'Регистрация неверифицированного владельца')]");
    private final SelenideElement closeButton = $x("//h4[contains(text(), 'Поиск владельца')]/following-sibling::button");
    private final SelenideElement nameOfEnterpriseField = $x("//textarea[@name='name']");
    private final SelenideElement typeOfEnterprise = $x("//select[@name='enterprise_type_id']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement typeResult = $("#select2--results > li:nth-child(5) > ul > li:nth-child(1)");
    private final SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private final SelenideElement districtSelection = $x("//select[@name='district_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement citySelection = $x("//select[@name='locality_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement streetSelection = $x("//select[@name='street_code']/following-sibling::span/span/span[@aria-controls='select2--container']");
    private final SelenideElement houseNumberDiv = $x("//div[contains(text(),'Дом')]/..//div[@class='col-6 mb-3 form-group pb-3']//input[@class='form-control']");
   private final SelenideElement serviceAreaSelection = $x("//select[@name='service_area_id']/following::span[@aria-labelledby='select2--container']");
    private final SelenideElement activateRegistrationButton = $("#submitFormsBtn");

    public AddEnterprisePage() {
        Selenide.sleep(2000);
        SelenideElement heading = $x("//h2[text()='Регистрация поднадзорного объекта']");
        heading.should(Condition.visible);
    }
    public void getNewOwnerLegalEntity() {
        chooseOwnerButton.should(Condition.enabled).click();
        innField.setValue("1");
        findButton.should(Condition.enabled).click();
        registrationOwnerFromCerber.should(Condition.enabled).click();
        registrationOwner.should(Condition.enabled).click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        if (ownerModalWindow.isOwnerNotRegistered()) {
            ownerModalWindow.getNewOwnerLegalEntity();
        }
        closeButton.should(Condition.enabled).click();
    }

    public EnterpriseCardPage getNewEnterpriseWithNewOwner
            (String nameOwner, String nameOfEnterprise, String district, String city, String serviceArea) {
        getNewOwnerLegalEntity();
        nameOfEnterpriseField.setValue(nameOfEnterprise);
        typeOfEnterprise.should(Condition.enabled).click();
        typeResult.should(Condition.enabled).click();
        input.pressEnter();
        typeOfEnterprise.pressEnter();
        districtSelection.should(Condition.enabled).click();
        input.setValue(district).pressEnter();
        citySelection.should(Condition.enabled).click();
        input.setValue(city).pressEnter();
        serviceAreaSelection.should(Condition.enabled).click();
        input.setValue(serviceArea).pressEnter();
        activateRegistrationButton.should(Condition.enabled).click();
        return new EnterpriseCardPage();
    }

    public EnterpriseCardPage getNewEnterprise
            (Enterprise enterprise) {
        chooseOwnerButton.should(Condition.enabled).click();
        OwnerModalWindow ownerModalWindow = new OwnerModalWindow();
        ownerModalWindow.getOwner(enterprise);
        nameOfEnterpriseField.setValue(enterprise.getName());
        typeOfEnterprise.should(Condition.enabled).click();
        input.setValue(enterprise.getTypeOfEnterprise()).pressEnter();
        districtSelection.should(Condition.enabled).click();
        input.setValue(enterprise.getDistrict()).pressEnter();
        citySelection.should(Condition.enabled).click();
        input.setValue(enterprise.getCity()).pressEnter();
        Selenide.sleep(1000);
        if (enterprise.getStreet() != null) {
            streetSelection.should(Condition.enabled).click();
            input.setValue(enterprise.getStreet()).pressEnter();
        }
        houseNumberDiv.setValue(enterprise.getHouse()).pressEnter();
        //houseNumber.setValue(enterprise.getHouse()).pressEnter();
        Selenide.sleep(2000);
        serviceAreaSelection.should(Condition.enabled).click();
        input.setValue(enterprise.getServiceArea()).pressEnter();
        activateRegistrationButton.should(Condition.enabled).click();
        return new EnterpriseCardPage();
    }
}