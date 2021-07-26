package greatFramework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class AppUI {

    static final Logger log = getLogger(lookup().lookupClass());
    private static WebDriver driver;
    public static Properties prop = new Properties();

    public static void setWebDriver(WebDriver d) {
        driver = d;
    }

    public static void get(String url) {
        driver.get(url);
        waitForNavigation(url);
        waitForLoad();
    }

    public static void getLoginPage() {
        get(resolveUrl("login"));
    }

    public static String resolveUrl(String path) {
        return getBaseUrl() + path;
    }

    public static void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public static void waitForNavigation (String url) {
        ExpectedCondition<Boolean> pageNavigateCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.URL").equals(url);
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageNavigateCondition);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getCurrentUserLogin() {
        return prop.getProperty("login");
    }

    public static String getCurrentUserPassword() {
        return prop.getProperty("password");
    }

    public static String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }

    public static Properties properties() {
        if (! prop.isEmpty()) {
            return prop;
        }

        String propFile = System.getProperty("properties.file");
        InputStream is;
        try {
            if (propFile == null || !propFile.equals("")) {
                java.net.URL resource = AbstractTest.class.getResource("/test.properties");
                is = resource.openStream();
            } else {
                is = new FileInputStream(propFile);
            }
            prop.load(is);
        } catch (IOException e) {
            log.error("Properties file is not found");
            e.printStackTrace();
        }
        return prop;
    }
}