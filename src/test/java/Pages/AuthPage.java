package Pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {
    // Авторизация
    private static SelenideElement email = $("input[name=email]");
    private static SelenideElement password = $("input[type=password]");
    private static SelenideElement authButton = $x("//button[contains(text(),'Авторизация')]");

    public AuthPage(){
       Assertions.assertTrue(isOnAuthPage());
    }
    public boolean isOnAuthPage() {
        email.isDisplayed();
        password.isDisplayed();
        return true;
    }
    @DisplayName("Авторизация")
    public static HomePage autoVet() {
        email.shouldBe(visible, Duration.ofSeconds(15));
        email.setValue("cheh@p.v");
        password.setValue("33221100");
        authButton.click();
        return new HomePage();
    }
}