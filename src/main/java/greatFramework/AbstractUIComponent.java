package greatFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractUIComponent {

    public void setInputValue(WebElement input, String value) {
        input.click();
        input.clear();
        input.sendKeys(value);
    }

    public WebElement waitForElement(By by, long timeOutInSeconds) {
        return new WebDriverWait(AppUI.getDriver(), timeOutInSeconds)
                .until(driver -> driver.findElement(by));
    }

    public List<WebElement> waitForElements(By by, long timeOutInSeconds) {
        return new WebDriverWait(AppUI.getDriver(), timeOutInSeconds)
                .until(driver -> driver.findElements(by));
    }

}
