package pages;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;

public class HomePage {

    // Variables
   private final Page page;

   // Locators
   private final String searchField = "#twotabsearchtextbox";
   private final String searchIcon = "#nav-search-submit-button";


    // Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    // Actions
    @Step("Add the keyword to the search field and click on the search  icon to get results")
    public SearchPage useSearchField(String keyword) {
        page.fill(searchField, keyword);
        page.click(searchIcon);
        return new SearchPage(page);
    }



}





/*
public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = browser.newPage();
        page.navigate("http://playwright.dev");
        assertThat(page).hasTitle(Pattern.compile("Playwright"));
        Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));
        assertThat(getStarted).hasAttribute("href", "/docs/intro");
        getStarted.click();
        assertThat(page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Installation"))).isVisible();
    }*/
