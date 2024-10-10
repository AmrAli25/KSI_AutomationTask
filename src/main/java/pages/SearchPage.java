package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class SearchPage {

    private final Page page;
    private final String searchResultText = "h2[class=\"a-size-medium-plus a-spacing-none a-color-base a-text-bold\"]";

    public SearchPage(Page page) {
        this.page = page;
    }

    // Validations
    public SearchPage verifySuccessfulSearchWithKeyword(){
        String searchTitle = page.textContent(searchResultText);
        assertEquals(searchTitle,"Results");
        return this;
    }

    @Step("Highlight the Results text")
    public void highlightSearchResults(){
        page.locator(searchResultText).highlight();
    }
}
