package checkout;

import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.HomePage;
import utils.PlaywrightFactory;

import java.util.Properties;

@Epic("Automation Exercise using Playwright - KSI")
@Feature("Add to cart and Checkout flow Function")
@Story("User sign in, add two items to cart and checkout to complete the order")
public class CheckoutAndCartTests {

    private PlaywrightFactory playwrightFactory;
    private Page page;
    private Properties properties;
    private final String keyword = "stand";
    private final String phone = "01505298035";
    private final String password = "123456789aA@";


    @BeforeMethod(description = "Setup browser instance")
    public void setupBrowser() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.init_properties();
        page = playwrightFactory.initBrowser(properties);
    }

    @Description("A user sign in using phone and password, adds two products to the cart, go to checkout" +
            "completes shipping address, choose payment method, and complete the order flow")
    @Test(description = "E2E for order flow")
    public void addToCartAndCheckoutFlow() {
        new HomePage(page).verifySingInButtonVisibility()
                .clickSignInButton()
                .verifySignInPageVisibility()
                .signUpUsingCredential(phone, password)
                .verifySingInSuccessfully()
                .navigateToCart()
                .clearTheCart()
                .useSearchFieldWithKeyword(keyword)
                .verifySuccessfulSearchWithKeyword(keyword)
                .selectProductToAddToCart()
                .verifyAddToCartButtonVisibility()
                .addItemsToTheCart()
                .verifyCartQuantity();
    }


    @AfterMethod(description = "Tear down browser instance")
    public void tearDown() {
        page.context().browser().close();
    }
}
