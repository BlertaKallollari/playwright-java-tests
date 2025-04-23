package ui;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;

public class DragAndDropUploadTest {
    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    public void createPage() {
        page = browser.newPage();
    }

    @AfterEach
    public void closePage() {
        page.close();
    }

    @AfterAll
    public static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @Test
    public void shouldVerifyDragAndDropIsVisibleAndClickable() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        Locator dragAndDropArea = page.locator("#drag-drop-upload");

        assertTrue(dragAndDropArea.isVisible(), "Drag-and-drop upload area should be visible.");

        assertTrue(dragAndDropArea.isEnabled(), "Drag-and-drop upload area should be clickable.");

        String filePath = "uploads/test-file.txt";

        page.locator("input#file-upload").setInputFiles(Paths.get(filePath));

        page.locator("input#file-submit").click();

        Locator errorPage = page.locator("text=Internal Server Error");
        if (errorPage.isVisible()) {
            System.out.println("Error Page: Internal Server Error was triggered after drag-and-drop.");
        } else {
            System.out.println("Drag-and-drop upload worked without Internal Server Error.");
        }
    }
}
