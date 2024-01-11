package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AnimalPassportPage {
    private SelenideElement heading = $x("//h2[text()='Паспорт животного']");
    private SelenideElement identificationNumber = $x("/html/body/div[2]/div/div[2]/div/main/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[2]");

    public AnimalPassportPage() {
        Selenide.sleep(2000);
        heading.shouldBe(visible);
    }

    public String getIdentificationNumber() {
        return identificationNumber.getText();
    }
}

