package dnh.integrationtesting.steps;

/**
 * author: Danesh Harjani
 */

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dnh.integrationtesting.TestContext;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginSteps {
    @Autowired
    TestContext context;

    @When("^I click on the login link$")
    public void I_click_on_the_login_link() {
    }

    @When("^I fill in the username \"([^\"]*)\"$")
    public void I_fill_in_the_username(String username) {
    }

    @When("^I fill in the password \"([^\"]*)\"$")
    public void I_fill_in_the_password(String password) {
    }

    @When("^I click on the login button$")
    public void I_click_on_the_login_button() {
    }

    @Then("^I should be logged in$")
    public void I_should_be_logged_in() {
    }

    @Then("^I should not be logged in$")
    public void I_should_not_be_logged_in() {
    }
}
