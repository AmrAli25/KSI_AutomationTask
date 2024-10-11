package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SignInPage {

    // Variables
    private final Page page;


    // Locators
    private final String emailFormField = "#ap_email";
    private final String passwordFormField = "#ap_password";
    private final String continueButton = "#continue";
    private final String submitButton = "#signInSubmit";
    private final String signInText = "h1[class=\"a-spacing-small\"]";


    // Constructor
    public SignInPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Enter Email address")
    public HomePage signUpUsingCredential(String phone, String password){
        page.locator(emailFormField).fill(phone);
        page.locator(continueButton).first().click();
        page.locator(passwordFormField).fill(password);
        page.locator(submitButton).click();
        return new HomePage(page);
    }


    // Validations
    @Step("Validate the visibility of the Sign in page")
    public SignInPage verifySignInPageVisibility() {
        assertEquals(page.locator(signInText).textContent().trim(), "Sign in");
        return this;
    }
}
