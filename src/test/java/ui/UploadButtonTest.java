package ui;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UploadButtonTest {
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @Test
    public void shouldVerifyUploadButtonIsVisibleAndClickable() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        Locator uploadButton = page.locator("input#file-upload[type='file']");

        assertTrue(uploadButton.isVisible(), "Upload button should be visible");

        assertTrue(uploadButton.isEnabled(), "Upload button should be clickable");
    }

    @AfterEach
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
    }
}
