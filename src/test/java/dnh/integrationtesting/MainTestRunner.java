package dnh.integrationtesting;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * author: Danesh Harjani
 */
@RunWith(Cucumber.class)
@Cucumber.Options(  strict = true,
                    tags="~@Ignore",
                    format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class MainTestRunner {
}
