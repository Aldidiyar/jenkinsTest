package test;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WikipediaAllureTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Search 'Selenium' on Wikipedia and verify results!")
    public void testWikipediaSearch() {
        openPage(System.getenv("https://www.wikipedia.org"));
        verifyPageTitle("Wikipedia11");
        searchFor("Selenium123123");
        verifySearchResultsContain("Selenium");
    }

    @Step("Open page: {url}")
    public void openPage(String url) {
        driver.get(url);
    }

    @Step("Verify page title contains '{expected}'")
    public void verifyPageTitle(String expected) {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains(expected), "Title does not contain expected text");
    }

    @Step("Search for '{query}'")
    public void searchFor(String query) {
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
    }

    @Step("Verify search results contain '{expected}'")
    public void verifySearchResultsContain(String expected) {
        WebElement firstHeading = driver.findElement(By.id("firstHeading"));
        Assert.assertTrue(firstHeading.getText().contains(expected),
                "First heading does not contain expected text");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
