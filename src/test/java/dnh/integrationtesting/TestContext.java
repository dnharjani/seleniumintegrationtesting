package dnh.integrationtesting;

import org.openqa.selenium.WebDriver;

/**
 * author: Danesh Harjani
 */
public class TestContext {

    private WebDriver driver;
    private PageURLFactory pageUrlFactory;
    private String baseUrl;
    private WebDriverFactory webDriverFactory;

    public TestContext(String baseUrl, PageURLFactory pageUrlFactory, WebDriverFactory webDriverFactory){
        this.baseUrl = baseUrl;
        this.pageUrlFactory = pageUrlFactory;
        this.webDriverFactory = webDriverFactory;
    }

    public WebDriverFactory getWebDriverFactory() {
        return webDriverFactory;
    }

    public void setWebDriverFactory(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPageURL(String page){
        return baseUrl + pageUrlFactory.getPageURL(page);
    }

    public void goToPage(String page){
        driver.get(getPageURL(page));
    }

    public void setupDriver(String driver){
        setDriver(webDriverFactory.getDriver(driver));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
