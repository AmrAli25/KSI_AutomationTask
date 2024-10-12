package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutPage {

    // Variables
    private final Page page;

    // Locators
    private final String changeAddressButton = "#addressChangeLinkId";
    private final String PaymentMethodRadioButton = "input[value=\"instrumentId=0h_PE_CUS_4a99cc0c-4a23-446f-bdea-3ee246a431da&isExpired=false&paymentMethod=CC&tfxEligible=false\"]";
    private final String submitPaymentMethod = "input[name=\"ppw-widgetEvent:SetPaymentPlanSelectContinueEvent\"]";
    private final String placeOrderButton = "#bottomSubmitOrderButtonId";
    private final String productPrice = "span[class=\"a-color-price\"]";
    private final String totalPrice = "span[id='subtotals-marketplace-spp-bottom']>span[class=\"a-size-medium a-color-price subtotal-amount a-text-bold\"]";
    private final String quantity = "span[class=\"a-button-text a-declarative\"]>span[class=\"a-dropdown-prompt\"]";


    // Constructor
    public CheckoutPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Select an address for the order")
    public CheckoutPage selectAddress() {
        page.locator(changeAddressButton).click();
        page.getByLabel("Amr Test Talaat Harb Street, Princess Tower, New Cairo City, Cairo, Egypt").check();
        page.getByTestId("Address_selectShipToThisAddress").click();
        return new CheckoutPage(page);
    }

    @Step("Select a payment method")
    public CheckoutPage selectPayment() {
        page.locator(PaymentMethodRadioButton).check();
        page.locator(submitPaymentMethod).first().click();
        return new CheckoutPage(page);
    }


    // Validations

    @Step("Verify Order Details is correct")
    public CheckoutPage verifyOrderDetails() {
        page.locator(placeOrderButton).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        int singleProductPrice = Integer.parseInt(page.locator(productPrice).textContent().trim().replace("EGP ", "").replace(".00", ""));
        int qnt = Integer.parseInt(page.locator(quantity).textContent().trim());
        String orderPrice = page.locator(totalPrice).textContent().trim().replace("Order total:EGP ", "").replace(".00", "");
        int orderPriceNum;
        if (orderPrice.contains(",")) {
            orderPriceNum = Integer.parseInt(orderPrice.replace(",", ""));
        } else
            orderPriceNum = Integer.parseInt(orderPrice);

        assertEquals((singleProductPrice * qnt), orderPriceNum);
        return this;
    }

    @Step("Verify Place order button is Enabled")
    public CheckoutPage verifyPlaceOrderButtonIsEnabled() {
        assertTrue(page.locator(placeOrderButton).isEnabled());
        System.out.println("Order Flow has Ended");
        return this;
    }

}
