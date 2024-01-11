package Pages;

import com.codeborne.selenide.WebDriverRunner;

public class EnterprisesPage {
        private static final String EXPECTED_URL = "https://v3.dev.regagro.ru/enterprises?";
        public boolean isOnEnterprisesPage() {
            return WebDriverRunner.url().equals(EXPECTED_URL);
        }
}
