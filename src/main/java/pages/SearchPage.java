package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import static org.testng.Assert.*;

public class SearchPage {

    // Locators
    private final String searchText = "//div[@class=\"a-section a-spacing-small a-spacing-top-small\"]//span[@class=\"a-color-state a-text-bold\"]";
    private final String categoryResultText = "div[class=\"fst-h1-st pageBanner\"]>h1";
    private final String sortButton = "#a-autoid-0-announce";
    private final String lowToHighButton = "#s-result-sort-select_1";
    private final String highToLowButton = "#s-result-sort-select_2";
    private final String avgReviewButton = "#s-result-sort-select_3";
    private final String firstProductPrice = "//div[@data-index='3']//span[@class='a-price-whole']";
    private final String lastProductPrice = "//div[@data-index='40']//span[@class='a-price-whole']";
    private final String firstProductRating = "//div[@data-index='3']//span[@class='a-icon-alt']";
    private final String lastProductRating = "//div[@data-index='40']//span[@class='a-icon-alt']";
    private final String freeShippingText = "div:nth-child(2) > span > .a-color-base";
    private final String singleProduct = "div[data-index='7']";


    // Variables
    private final Page page;
    private final String lowToHigh = "Price: Low to High";
    private final String highToLow = "Price: High to Low";
    private final String avgReview = "Avg. Customer Review";
    private final String filterBrand = "Lenovo";
    private final String filterShipping = "Free Shipping";
    private final String freeShipping = "Fulfilled by Amazon - FREE Shipping";
    private final String[] multiFilters = {
            "Fulfilled Fulfilled by Amazon",
            "Windows 11 Home",
            "NVIDIA",
            "Intel Core i7",
            "Gaming"
    };

    public enum sortingOptions {
        LOW_TO_HIGH,
        HIGH_TO_LOW,
        AVG_REVIEW
    }

    // Constructor
    public SearchPage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Select Low to High sorting option from Dropdown menu")
    public SearchPage sortProductLowToHigh() {
        page.locator(sortButton).getByText("Featured").click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(lowToHigh)).locator(lowToHighButton).click();
        return this;
    }

    @Step("Select High to Low sorting option from Dropdown menu")
    public SearchPage sortProductHighToLow() {
        page.locator(sortButton).getByText("Featured").click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(highToLow)).locator(highToLowButton).click();
        return this;
    }

    @Step("Select Avg customer review sorting option from Dropdown menu")
    public SearchPage sortProductAvgReview() {
        page.locator(sortButton).click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(avgReview)).locator(avgReviewButton).click();
        return this;
    }

    @Step("Select A filterBrand option (Lenovo)")
    public SearchPage filterByBrand() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterBrand).setExact(true)).click();
        return this;
    }

    @Step("Select A filterBrand option (Free Shipping)")
    public SearchPage filterByFreeShipping() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterShipping)).click();
        return this;
    }

    @Step("Select A Multi filters options")
    public SearchPage applyMultiFilters() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(multiFilters[0])).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterBrand).setExact(true)).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(multiFilters[1])).click();
        page.getByLabel("CPU Model Manufacturer").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(multiFilters[2])).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(multiFilters[3]).setExact(true)).click();
        page.getByLabel("Keyboard Description").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(multiFilters[4])).click();
        return this;
    }

    @Step("Select an item to add to cart")
    public ProductPage selectProductToAddToCart() {
        page.locator(singleProduct).click();
        return new ProductPage(page);
    }


    // Validations
    @Step("Verify correct search results for the keyword provided in search bar")
    public SearchPage verifySuccessfulSearchWithKeyword(String searchKeyword) {
        assertEquals(page.textContent(searchText), "\"" + searchKeyword + "\"");
        return this;
    }

    @Step("Verify correct search results for the category selected from search bar")
    public SearchPage verifySuccessfulSearchWithCategory(String category) {
        assertEquals(page.textContent(categoryResultText), category);
        return this;
    }

    @Step("Verify correct search results for the sorting option selected")
    public SearchPage verifySuccessfulSearchResultsSorting(sortingOptions sortingOption) {
        switch (sortingOption) {
            case LOW_TO_HIGH:
                assertTrue(Integer.parseInt((page.locator(firstProductPrice).textContent().trim().replace(".", "").replace(",", ""))) <
                        Integer.parseInt(page.locator(lastProductPrice).textContent().trim().replace(".", "").replace(",", "")));
                break;
            case HIGH_TO_LOW:
                assertTrue(Integer.parseInt((page.locator(firstProductPrice).textContent().trim().replace(".", "").replace(",", ""))) >
                        Integer.parseInt(page.locator(lastProductPrice).textContent().trim().replace(".", "").replace(",", "")));
                break;
            case AVG_REVIEW:
                assertNotEquals(page.locator(lastProductRating).textContent(), page.locator(firstProductRating).textContent());
                break;
            default:
                System.out.println("Something went wrong -- Choose correct Enum");
        }
        return this;
    }

    @Step("Verify correct filter Brand option is applied")
    public SearchPage verifySuccessfulSearchResultsFilteringByBrand() {
        assertTrue(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterBrand).setExact(true)).isChecked());
        return this;
    }

    @Step("Verify correct filter Free shipping option is applied")
    public SearchPage verifySuccessfulSearchResultsFilteringByFreeShipping() {
        assertTrue(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterShipping)).isChecked());
        assertEquals(page.locator(freeShippingText).first().textContent(), freeShipping);
        return this;
    }

    @Step("Verify correct filters is applied ")
    public SearchPage verifySuccessfulSearchResultsMultiFilters() {
        assertTrue(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(multiFilters[0])).isChecked());
        assertTrue(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(filterBrand).setExact(true)).isChecked());
        assertTrue(page.getByLabel("Operating System").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(multiFilters[1])).isChecked());
        assertTrue(page.getByLabel("CPU Model Manufacturer").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(multiFilters[2])).isChecked());
        assertTrue(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(multiFilters[3]).setExact(true)).isChecked());
        assertTrue(page.getByLabel("Keyboard Description").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(multiFilters[4])).isChecked());
        return this;
    }


    @Step("Verify product visibility ")
    public SearchPage verifyProductVisibility() {
        page.locator(singleProduct).isVisible();
        return this;
    }


}
