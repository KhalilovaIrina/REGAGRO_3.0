package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {
    // Авторизация
    private final SelenideElement email = $("input[name=email]");
    private final SelenideElement password = $("input[type=password]");
    private final SelenideElement authButton = $x("//button[contains(text(),'Авторизация')]");

    public AuthPage(){
        email.should(Condition.visible);
        password.should(Condition.visible);
    }
    public boolean isOnAuthPage() {
        email.should(Condition.visible);
        password.should(Condition.visible);
        return true;
    }
    // Авторизация Ветеринарный врач
    public HomePage autoVet() {
        email.shouldBe(Condition.visible, Duration.ofSeconds(15));
        email.setValue("cheh@p.v");
        password.setValue("33221100");
        authButton.should(Condition.enabled).click();
        return new HomePage();
    }
    //Авторизация суперадмин
    public HomePage authSuperAdmin() {
        email.shouldBe(Condition.visible, Duration.ofSeconds(15));
        email.setValue("admin@regagro.net");
        password.setValue("44qsaTeF@p8I");
        authButton.should(Condition.enabled).click();
        return new HomePage();
    }
}