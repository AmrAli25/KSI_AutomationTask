package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import static org.testng.Assert.assertTrue;

public class ProductPage {

    // Variables
    private final Page page;
    private final String quantity = "2";
    private final String addToCart = "Add to Cart";


    // Locators

    // Constructor
    public ProductPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Add two items from the product to the cart")
    public CartPage addItemsToTheCart() {
        page.getByText("Quantity:1").click();
        page.getByLabel(quantity, new Page.GetByLabelOptions().setExact(true)).getByText(quantity).click();
        page.getByLabel(addToCart, new Page.GetByLabelOptions().setExact(true)).click();
        return new CartPage(page);
    }

    // Validations
    @Step("Verify add to cart button is visible")
    public ProductPage verifyAddToCartButtonVisibility() {
        assertTrue(page.locator("#add-to-cart-button").isEnabled());
        return this;
    }

}
