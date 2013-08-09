package dnh.integrationtesting.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dnh.integrationtesting.TestContext;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * author: Danesh Harjani
 */
public class GlobalSteps {
    @Autowired
    TestContext context;

    @Autowired
    String username;

    @Autowired
    String password;

    @Autowired
    String webDriverType;

    @Before(order = 1, value="@nojs")
    public void setupNoJS(){
        context.setupDriver(webDriverType + "_nojs");
        context.goToPage("Home");
    }

    @Before(order = 2)
    public void setup(){
        if(context.getDriver() == null){
            context.setupDriver(webDriverType);
            context.goToPage("Home");
        }
    }

    @Before(order = 3, value ="@login")
    public void loginBefore(){
        login(username, password);
    }

    @Before(order = 4, value ="@mobile")
    public void mobile(){
        context.getDriver().manage().window().setSize(new Dimension(320, 480));
    }

    @Before(order = 4, value ="@tablet")
    public void tablet(){
        context.getDriver().manage().window().setSize(new Dimension(1024, 768));
    }

    @Before(order = 4, value ="@desktop")
    public void desktop(){
        context.getDriver().manage().window().maximize();
    }



    @After(order = 2)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }

    @After(order = 1)
    public void tearDown(){
        try{
            context.getDriver().quit();
            context.setDriver(null);
        }
        catch(Exception e){
            // We're quitting anyway.
        }
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void I_am_on_the_page(String page) {
        context.getDriver().get(context.getPageURL(page));
    }

    @Then("^I should be on the \"([^\"]*)\" page$")
    public void I_should__be_on_the_page(String page) {
        assertTrue(context.getDriver().getCurrentUrl().contains(context.getPageURL(page)));
    }

    private void login(String username, String password){

    }
}
