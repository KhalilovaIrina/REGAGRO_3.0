package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dataGenerator.DataGenerator;
import entities.Enterprise;

import static com.codeborne.selenide.Selenide.$x;

public class OwnerModalWindow {
    private final SelenideElement headingAdd = $x("//h4[text()='Регистрация неверифицированного владельца']");
    private final SelenideElement headingEdit = $x("//h4[text()='Редактирование владельца']");
    private final SelenideElement ownerTypeSelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[1]/div[2]/div/span/span[1]/span");
    private final SelenideElement input = $x("/html/body/span/span/span[1]/input");
    private final SelenideElement innField10 = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[2]/div[2]/div/input");
    private final SelenideElement innField = Selenide.$("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input");
    private final SelenideElement nameField = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[4]/div[2]/textarea");
    private final SelenideElement regionSelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[6]/div[2]/div[2]/div/span");
    private final SelenideElement citySelection = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div/div[2]/div/div/div[2]/div[2]/form/div[6]/div[4]/div[2]/div");
    private final SelenideElement registrationButton = $x("//button[contains(text(),'Зарегистрировать')]");
    private final SelenideElement okButton = $x("//button[contains(text(),'Ok')]");
    private final SelenideElement findButton = $x("//button[contains(text(),'Найти')]");
    private final SelenideElement description = Selenide.$("div[class=item-descritpion]");
    private final SelenideElement chooseButton = $x("//button[contains(text(),'Выбрать')]");
    DataGenerator dataGenerator = new DataGenerator();
    public boolean isOwnerNotRegistered() {
        return headingAdd.isDisplayed() & headingEdit.isDisplayed();
    }

    public void getNewOwnerLegalEntity() {
        ownerTypeSelection.should(Condition.enabled).click();
        input.setValue("Юридическое");
        input.pressEnter();
        innField10.setValue(dataGenerator.getNumber(10));
        nameField.setValue(dataGenerator.getFullName());
        regionSelection.should(Condition.enabled).click();
        input.setValue("Орловская");
        input.pressEnter();
        Selenide.sleep(3000);
        citySelection.should(Condition.enabled).click();
        input.setValue("Ливны");
        input.pressEnter();
        registrationButton.should(Condition.enabled).click();
    }

    public void editInn(String newInn) {
        innField10.should(Condition.enabled).clear();
        innField10.setValue(newInn);
        okButton.should(Condition.enabled).click();
    }

    public void getOwner(Enterprise enterprise) {
        innField.should(Condition.enabled).click();
        innField.setValue(enterprise.getOwnerInn());
        findButton.should(Condition.enabled).click();
        description.should(Condition.enabled).click();
        chooseButton.should(Condition.enabled).click();
    }
}
