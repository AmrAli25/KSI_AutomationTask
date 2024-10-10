package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;

public class HomePage {

    // Variables
    private final Page page;

    // Locators
    private final String searchField = "#twotabsearchtextbox";
    private final String searchIcon = "#nav-search-submit-button";
    private final String categoriesDropDown = "select[aria-describedby=\"searchDropdownDescription\"]";


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


}
