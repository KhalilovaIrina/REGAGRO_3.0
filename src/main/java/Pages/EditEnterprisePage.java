package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EditEnterprisePage {
     private final SelenideElement nameOfEnterpriseField = $x("//textarea[@name='name']");
    private final SelenideElement saveButton = $x("//button[@id='submitFormsBtn']");
    public EditEnterprisePage() {
        Selenide.sleep(2000);
        SelenideElement heading = Selenide.$x("//h2[text()='Редактирование поднадзорного объекта']");
        heading.should(Condition.visible);
    }
    public EnterpriseCardPage getEditEnterprise(String newNameOfEnterprise){
        Selenide.sleep(5000);
        nameOfEnterpriseField.should(Condition.enabled).clear();
        nameOfEnterpriseField.setValue(newNameOfEnterprise);
        Selenide.sleep(3000);
        saveButton.should(Condition.enabled).click();
        Selenide.sleep(3000);
        return new EnterpriseCardPage();
    }
}
