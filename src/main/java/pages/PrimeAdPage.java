package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class PrimeAdPage {

    // Variables
    private Page page;

    // Locators
    private final String byPassButton = "#prime-declineCTA";
    private final String byPassButtonMain = "#prime-interstitial-nothanks-button";

    // Constructor
    public PrimeAdPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Bypass Prime Ad page")
    public CheckoutPage byPassPrimeAd() {
        if(page.locator(byPassButton).isVisible())
            page.locator(byPassButton).click();
        else{
            page.locator(byPassButtonMain).click();
        }

        page.reload();
        return new CheckoutPage(page);
    }
}
