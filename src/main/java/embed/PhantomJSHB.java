package embed;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;

/**
 * Created by ipseeta on 6/10/17.
 */
public class PhantomJSHB {
    private static String USER_AGENT ="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static DesiredCapabilities desiredCaps ;
    private static WebDriver driver ;
    public static void main(String[] args) {

        System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);
        initPhantomJS();
        driver.get("https://github.com/Ipseeta");

        System.out.println(driver.getTitle());
        driver.quit();

    }
    public static void initPhantomJS(){
        desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "User-Agent", USER_AGENT);
        desiredCaps = new DesiredCapabilities();
        desiredCaps.setJavascriptEnabled(true);
        desiredCaps.setCapability("takesScreenshot", false);
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/ipseeta/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");

        ArrayList<String> cliArgsCap = new ArrayList();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--webdriver-loglevel=ERROR");

        //desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        driver = new PhantomJSDriver(desiredCaps);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
}
