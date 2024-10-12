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
    private final String keyword = "headphones wired";
    private final String phone = "01505298035";
    private final String password = "123456789aA@";


    @BeforeMethod(description = "Setup browser instance")
    public void setupBrowser() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.init_properties();
        page = playwrightFactory.initBrowser(properties);
    }

    @Test(description = "E2E for order flow - GUI")
    @Description("""
            A user sign in using phone and password, adds two products to the cart, go to checkout
            completes shipping address, choose payment method, and complete the order flow
            Steps:
            - User sign in with Phone and Password
            - Check for products in the cart and clear them
            - Search with keyword
            - Select a product
            - Add two quantity of the product to the cart
            - Go to checkout page
            - Select Shipping address
            - Select payment method
            - Verify order price is correct
            """)
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
                .verifyProductVisibility()
                .selectProductToAddToCart()
                .verifyAddToCartButtonVisibility()
                .addItemsToTheCart()
                .verifyCartQuantity()
                .proceedToCheckout()
                .byPassPrimeAd()
                .selectAddress()
                .selectPayment()
                .verifyOrderDetails()
                .verifyPlaceOrderButtonIsEnabled();
    }


    @AfterMethod(description = "Tear down browser instance")
    public void tearDown() {
        page.context().browser().close();
    }
}
