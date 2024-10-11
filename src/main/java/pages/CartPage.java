package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class CartPage {

    // Variables
    private final Page page;


    // Locators
    private final String proceedButton = "input[data-feature-id='proceed-to-checkout-action']";
    private final String logoButton = "#nav-logo-sprites";
    private final String deleteButton = "input[value='Delete']";
    private final String cartCount = "#nav-cart-count";
    private final String quantity = "2";

    // Constructor
    public CartPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Continue to the checkout page")
    public PrimeAdPage proceedToCheckout() {
        page.locator(proceedButton).click();
        return new PrimeAdPage(page);
    }

    @Step("Navigate to the cart")
    public HomePage clearTheCart() {
        if (!page.locator(cartCount).textContent().trim().equals("0"))
            page.click(deleteButton);
        page.click(logoButton);
        return new HomePage(page);
    }

    // Validations
    @Step("Verify the quantity added to the cart")
    public CartPage verifyCartQuantity() {
        assertEquals(page.locator(cartCount).textContent().trim(), quantity);
        return this;
    }
}
