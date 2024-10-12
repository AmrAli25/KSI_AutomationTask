package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class PrimeAdPage {

    // Variables
    private Page page;

    // Locators
    private final String byPassButton = "#prime-declineCTA";

    // Constructor
    public PrimeAdPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Bypass Prime Ad page")
    public CheckoutPage byPassPrimeAd() {
        page.locator(byPassButton).click();
        page.reload();
        return new CheckoutPage(page);
    }
}
