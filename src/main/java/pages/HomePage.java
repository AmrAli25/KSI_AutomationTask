package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    // Variables
    private final Page page;

    // Locators
    private final String searchField = "#twotabsearchtextbox";
    private final String searchIcon = "#nav-search-submit-button";
    private final String categoriesDropDown = "select[aria-describedby=\"searchDropdownDescription\"]";
    private final String signInButton = "#nav-link-accountList";
    private final String cartButton = "#nav-cart";
    private final String userNameText = "#nav-link-accountList-nav-line-1";


    // Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Add the keyword to the search field and click on the search")
    public SearchPage useSearchFieldWithKeyword(String keyword) {
        page.fill(searchField, keyword);
        page.click(searchIcon);
        return new SearchPage(page);
    }

    @Step("Select the category you want to search with (Electronics) and start search")
    public SearchPage useSearchFieldWithCategory(String category) {
        page.click(categoriesDropDown);
        page.selectOption(categoriesDropDown, new SelectOption().setLabel(category));
        page.click(searchIcon);
        return new SearchPage(page);
    }

    @Step("Click on sign in button")
    public SignInPage clickSignInButton() {
        page.click(signInButton);
        return new SignInPage(page);
    }

    @Step("Navigate to the cart")
    public CartPage navigateToCart() {
        page.click(cartButton);
        return new CartPage(page);
    }


    //Validations
    @Step("Verify the visibility of Search Bar")
    public HomePage verifySearchBarVisablility() {
        assertTrue(page.locator(searchField).isVisible());
        return this;
    }

    @Step("Verify the visibility of Sign in button")
    public HomePage verifySingInButtonVisibility() {
        assertTrue(page.locator(signInButton).isVisible());
        return this;
    }

    @Step("Verify the visibility of Sign in button")
    public HomePage verifySingInSuccessfully() {
        assertEquals(page.locator(userNameText).textContent(), "Hello, Amr");
        return this;
    }


}
