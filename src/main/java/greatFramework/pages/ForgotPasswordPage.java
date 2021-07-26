package greatFramework.pages;

import greatFramework.AppUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private WebElement getHeader() {
        return AppUI.getDriver().findElement(By.xpath("//h1[text() =\"Reset Password\"]"));
    }

    public Boolean isOpened() {
        return getHeader().isDisplayed();
    }
}
