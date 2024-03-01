package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dataGenerator.DataGenerator;
import entities.Enterprise;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OwnerModalWindow {
    private SelenideElement headingAdd = $x("//h4[text()='Регистрация неверифицированного владельца']");
    private SelenideElement headingEdit = $x("//h4[text()='Редактирование владельца']");
    private SelenideElement ownerTypeSelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[1]/div[2]/div/span/span[1]/span");
    private SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private SelenideElement innField10 = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[2]/div[2]/div/input");
    private SelenideElement innField = $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input");
    private SelenideElement kppField9 = $x("//*[@id=addOwnerForm]/div[3]/div[2]/div/input");
    private SelenideElement nameField = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[4]/div[2]/textarea");
    private SelenideElement regionSelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[6]/div[2]/div[2]/div/span");
    private SelenideElement citySelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[6]/div[4]/div[2]/div");
    private SelenideElement registrationButton = $x("//button[contains(text(),'Зарегистрировать')]");
    private SelenideElement okButton = $x("//button[contains(text(),'Ok')]");
    private SelenideElement findButton = $x("//button[contains(text(),'Найти')]");
    private SelenideElement description = $("div[class=item-descritpion]");
    private SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");

    public boolean isOwnerNotRegistered() {
        if (headingAdd.isDisplayed() & headingEdit.isDisplayed()) {
            return true;
        } else return false;
    }

    private static String inn = DataGenerator.getNumber(10);

    public void getNewOwnerLegalEntity(String inn) {
        ownerTypeSelection.click();
        input.setValue("Юридическое");
        input.pressEnter();
        innField10.setValue(inn);
        nameField.setValue(DataGenerator.getFullName());
        regionSelection.click();
        input.setValue("Орловская");
        input.pressEnter();
        Selenide.sleep(3000);
        citySelection.click();
        input.setValue("Ливны");
        input.pressEnter();
        registrationButton.click();
    }

    public String getInnNewOwner() {
        return inn;
    }

    public void editInn(String newInn) {
        innField10.clear();
        innField10.setValue(newInn);
        okButton.click();
    }

    public void getOwner(Enterprise enterprise) {
        innField.click();
        innField.setValue(enterprise.ownerInn);
        findButton.click();
        description.click();
        chooseButton.click();
    }

}
