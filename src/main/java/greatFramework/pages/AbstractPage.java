package greatFramework.pages;

import greatFramework.AbstractUIComponent;
import greatFramework.AppUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractPage extends AbstractUIComponent {

    static final Logger log = getLogger(lookup().lookupClass());

    public String getTitle() {
        return AppUI.getDriver().getTitle();
    }

    public String getCurrentUrl() {
        return AppUI.getDriver().getCurrentUrl();
    }

    public void waitForLoadPage(String url) {
        AppUI.waitForNavigation(AppUI.resolveUrl(url));
        AppUI.waitForLoad();
        log.debug("URL is loaded: " + url);
    }

    public List<String> getWarnings() {
        ArrayList<String> warnings = new ArrayList<String>();
        List<WebElement> elements = waitForElements(By.cssSelector("p[id*=\"-helper-text\"]"), 3);
        for (WebElement e:elements) {
            warnings.add(e.getText());
        }
        return warnings;
    }
}
