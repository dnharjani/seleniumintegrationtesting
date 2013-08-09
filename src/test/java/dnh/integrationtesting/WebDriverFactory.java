package dnh.integrationtesting;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

/**
 * author: Danesh Harjani
 */

// Builds and return the requested WebDriver
@Component
public class WebDriverFactory {
    private FirefoxDriver fireFoxDriver;
    private HtmlUnitDriver htmlUnitDriver;
    private HtmlUnitDriver htmlUnitDriverNoJs;

    private String displayPort;
    private String proxyHost;
    private Integer proxyPort;

    public WebDriverFactory(){

    }

    public WebDriver getDriver(String driverName){
        if(driverName.equals("firefox"))
            return getFireFoxDriver();
        else if(driverName.equals("firefox_nojs"))
            return getFireFoxDriverNoJS();
        else if(driverName.equals("fresh_firefox"))
            return getNewFireFoxDriver();
        else if(driverName.equals("fresh_firefox_nojs"))
            return getNewFireFoxDriverNoJS();
        else if(driverName.equals("default"))
            return getHtmlUnitDriver();
        else if(driverName.equals("default_nojs"))
            return getHtmlUnitDriverNoJs();
        else if(driverName.equals("fresh_default"))
            return new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
        else if(driverName.equals("fresh_default_nojs"))
            return new HtmlUnitDriver(false);

        return null;

    }

    public String getDisplayPort() {
        return displayPort;
    }

    public void setDisplayPort(String displayPort) {
        this.displayPort = displayPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    private FirefoxDriver getFireFoxDriver(){
        if(fireFoxDriver == null){
            fireFoxDriver = new FirefoxDriver(configureFireFoxBinary(), configureFireFoxProfile(true), configureFireFoxCapabilities(true));
        }
        return fireFoxDriver;
    }

    private FirefoxDriver getFireFoxDriverNoJS(){
        if(fireFoxDriver == null){
            fireFoxDriver = new FirefoxDriver(configureFireFoxBinary(), configureFireFoxProfile(false), configureFireFoxCapabilities(false));
        }
        return fireFoxDriver;
    }

    private FirefoxDriver getNewFireFoxDriver(){
        return new FirefoxDriver(configureFireFoxBinary(), configureFireFoxProfile(true), configureFireFoxCapabilities(true));
    }

    private FirefoxDriver getNewFireFoxDriverNoJS(){
        return new FirefoxDriver(configureFireFoxBinary(), configureFireFoxProfile(false), configureFireFoxCapabilities(false));
    }

    private FirefoxBinary configureFireFoxBinary(){
        FirefoxBinary binary = new FirefoxBinary();
        if(displayPort != null){
            binary.setEnvironmentProperty("DISPLAY", displayPort);
        }
        return binary;
    }

    private FirefoxProfile configureFireFoxProfile(boolean enableJS){
        FirefoxProfile profile = new FirefoxProfile();
        if(proxyHost != null && proxyPort != null){
            profile.setPreference("network.proxy.type", 1);
            profile.setPreference("network.proxy.http", proxyHost);
            profile.setPreference("network.proxy.http_port", proxyPort);
            profile.setPreference("network.proxy.ssl", proxyHost);
            profile.setPreference("network.proxy.ssl_port", proxyPort);
        }
        profile.setPreference("javascript.enabled", enableJS);
        return profile;
    }

    private DesiredCapabilities configureFireFoxCapabilities(boolean enableJS){
        DesiredCapabilities cap = new DesiredCapabilities();
        if(proxyHost != null && proxyPort != null){
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(proxyHost + ":" + proxyPort);
            proxy.setHttpsProxy(proxyHost + ":" + proxyPort);
            cap.setCapability(CapabilityType.PROXY, proxy);
        }
        cap.setJavascriptEnabled(enableJS);
        return cap;
    }

    private HtmlUnitDriver getHtmlUnitDriver(){
        if(htmlUnitDriver == null){
            htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
        }
        return htmlUnitDriver;
    }

    private HtmlUnitDriver getHtmlUnitDriverNoJs(){
        if(htmlUnitDriverNoJs == null){
            htmlUnitDriverNoJs = new HtmlUnitDriver(false);
        }
        return htmlUnitDriverNoJs;
    }
}
