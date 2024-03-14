package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {
    // Авторизация
    private static SelenideElement email = Selenide.$("input[name=email]");
    private static SelenideElement password = Selenide.$("input[type=password]");
    private static SelenideElement authButton = Selenide.$x("//button[contains(text(),'Авторизация')]");

    public AuthPage(){

        email.isDisplayed();
        password.isDisplayed();
    }
    public boolean isOnAuthPage() {
        email.isDisplayed();
        password.isDisplayed();
        return true;
    }
    @DisplayName("Авторизация Ветеринарный врач")
    public static HomePage autoVet() {
        email.shouldBe(Condition.visible, Duration.ofSeconds(15));
        email.setValue("cheh@p.v");
        password.setValue("33221100");
        authButton.click();
        return new HomePage();
    }
    @DisplayName("Авторизация суперадмин")
    public static HomePage authSuperAdmin() {
        email.shouldBe(Condition.visible, Duration.ofSeconds(15));
        email.setValue("admin@regagro.net");
        password.setValue("44qsaTeF@p8I");
        authButton.click();
        return new HomePage();
    }
}