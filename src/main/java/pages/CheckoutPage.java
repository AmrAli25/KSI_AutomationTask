package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class CheckoutPage {

    // Variables
    private final Page page;


    // Locators

    // Constructor
    public CheckoutPage(Page page){
        this.page = page;
    }

    // Actions
    @Step("Add two items from the product to the cart")
    public CheckoutPage addItemsToTheCart(){
        return new CheckoutPage(page);
    }

    // Validations
}
