package ui;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class NoFileUploadTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Test
    @DisplayName("Should show error when clicking upload with no file selected")
    void shouldShowErrorWhenNoFileSelected() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        page.click("input[type='submit']");

        page.waitForLoadState();

        String pageContent = page.content().toLowerCase();

        System.out.println("Page content after upload with no file:\n" + pageContent);

        boolean containsError = pageContent.contains("internal server error");

        assertTrue(containsError, "Expected 'Internal Server Error' message when uploading with no file.");
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}

