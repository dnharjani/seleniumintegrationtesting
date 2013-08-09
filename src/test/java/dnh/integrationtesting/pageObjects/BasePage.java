package dnh.integrationtesting.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 * author: Danesh Harjani
 */
public abstract class BasePage {
    final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
