import com.codeborne.selenide.Configuration;
import data.RegistrationHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class RegClassTest {

    public RegistrationHelper helper = new RegistrationHelper();
    @BeforeAll
    static void setup() {
        Configuration.headless = true;
        open("https://v3.dev.regagro.ru/");
        RegistrationHelper.autoVet();
        //Configuration.holdBrowserOpen = true;
    }
    @DisplayName("Многократная регистрация животного")
    @RepeatedTest(6)
    void regIndividualAnimals() {
        int max = 100;
        int count = 0;
        helper.firstRegistration();
        do {
            helper.nextRegistration();
            count++;
        }
        while (count < max);
    }
}


