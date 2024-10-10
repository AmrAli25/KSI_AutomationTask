import com.microsoft.playwright.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.HomePage;
import utils.PlaywrightFactory;
import utils.TestNGListener;

import java.util.Properties;

import static org.testng.Assert.assertEquals;

@Epic("Automation Exercise using Playwright - KSI")
@Feature("Search Function")
@Story("Search with keywords and Category")
@Listeners(TestNGListener.class)
public class SearchTests {

    private PlaywrightFactory playwrightFactory;
    private Page page;
    private Properties properties;
    private HomePage homePage;

    @BeforeClass(description = "Setup browser instance")
    public void setupBrowser() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.init_properties();
        page = playwrightFactory.initBrowser(properties);
        homePage = new HomePage(page);
    }

    @Description("A user search with a keyword in the search field, and validate correct navigation and results")
    @Test(description = "Search with keyword in the search field ")
    void searchTests() {
       homePage.useSearchField("laptops")
               .verifySuccessfulSearchWithKeyword()
               .highlightSearchResults();
    }

}
