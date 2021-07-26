package greatFramework.pages;

import greatFramework.AppUI;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private final String AUTH_ERROR = "Your email or password wasn't recognized.";

    private WebElement getLoginInput() {
        return AppUI.getDriver().findElement(By.cssSelector("[id=\"email\"]"));
    }

    public void setLogin(String value) {
        setInputValue(getLoginInput(), value);
    }

    private WebElement getPasswordInput() {
        return AppUI.getDriver().findElement(By.cssSelector("[id=\"password\"]"));
    }

    public void setPassword(String value) {
        setInputValue(getPasswordInput(), value);
    }

    private WebElement getLoginButton() {
        return AppUI.getDriver().findElement(By.cssSelector("button[type=\"submit\"]"));
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    private WebElement getForgotPasswordLink() {
        return  AppUI.getDriver().findElement(By.cssSelector("a[href=\"/account/forgot-password\"]"));
    }

    public ForgotPasswordPage clickForgotPassword() {
        getForgotPasswordLink().click();
        waitForLoadPage("account/forgot-password");
        return new ForgotPasswordPage();
    }

    private WebElement getSignUpLink() {
        return  AppUI.getDriver().findElement(By.xpath("//a[text() =\"Sign Up\"]"));
    }

    public SignUpPage clickSignUp() {
        getSignUpLink().click();
        waitForLoadPage("signup");
        return new SignUpPage();
    }

    public Boolean isAuthError() {
        return waitForElement(By.xpath("//p[text() =\""+ AUTH_ERROR +"\"]"), 3) != null;
    }

}
