package allTests.Login;

import greatFramework.AbstractTest;
import greatFramework.AppUI;
import greatFramework.pages.ForgotPasswordPage;
import greatFramework.pages.LoginPage;
import greatFramework.pages.SignUpPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests extends AbstractTest {

    private final String PASSWORD_ERROR = "Please enter your password";
    private final String WRONG_PASSWORD = "wrongPassword";

    private LoginPage page;

    public LoginPageTests(WebDriver d) {
        super(d);
    }

    @BeforeEach
    public void gotoLoginPage() {
        AppUI.getLoginPage();
        page = new LoginPage();
    }

    @Test
    @DisplayName("Successfully login with correct credentials")
    public void logIn() {
        page.setLogin(AppUI.getCurrentUserLogin());
        page.setPassword(AppUI.getCurrentUserPassword());
        page.clickLoginButton();
        page.waitForLoadPage("offerings");
        assertTrue(page.getCurrentUrl().endsWith("offerings"));
    }

    @Disabled
    @Test
    @DisplayName("Successfully click and open forgot-password page")
    public void forgotPassword() {
        ForgotPasswordPage fgPage = page.clickForgotPassword();
        assertTrue(fgPage.isOpened());
    }

    @Test
    @DisplayName("Failed test to click and open signup page")
    public void signUp() {
        SignUpPage suPage = page.clickSignUp();
        assertTrue(page.getCurrentUrl().endsWith("signupt"));
    }

    @Test
    @DisplayName("Get warning when password is empty")
    public void emptyLogin() {
        page.setLogin(AppUI.getCurrentUserLogin());
        page.clickLoginButton();
        assertEquals(page.getWarnings().get(0), PASSWORD_ERROR);
    }

    @Test
    @DisplayName("Cannot login with wrong password")
    public void wrongPassword() {
        page.setLogin(AppUI.getCurrentUserLogin());
        page.setPassword(WRONG_PASSWORD);
        page.clickLoginButton();
        assertTrue(page.isAuthError());
    }
}
