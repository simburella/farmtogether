package greatFramework;

import io.github.bonigarcia.seljup.Options;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class AbstractTest {

    private WebDriver driver;

    public AbstractTest(WebDriver d) {
        driver = d;
        driver.manage().window().setSize(new Dimension(1280, 980));
        AppUI.setWebDriver(driver);
    }

    @RegisterExtension
    static SeleniumJupiter seleniumJupiter = new SeleniumJupiter();

    @Options
    static ChromeOptions chromeOptions;

    @BeforeAll
    static void setup() {
        seleniumJupiter.getConfig().setDefaultBrowser(AppUI.properties().getProperty("browser"));
        seleniumJupiter.getConfig().enableScreenshotAtTheEndOfTests();
        seleniumJupiter.getConfig().takeScreenshotAsPng();
        seleniumJupiter.getConfig().setOutputFolder(AppUI.properties().getProperty("screenshotFolder"));

        chromeOptions = new ChromeOptions();
        if (AppUI.properties().getProperty("headless").toLowerCase().equals("true")) {
            chromeOptions.addArguments("--headless");
        }
    }


}
