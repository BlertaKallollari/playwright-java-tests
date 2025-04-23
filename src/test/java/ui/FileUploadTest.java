package ui;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

public class FileUploadTest {
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
    public void shouldUploadFileAndVerifyFileName() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        String filePath = "uploads/test-file.txt";
        File file = new File(filePath);
        assertTrue(file.exists(), "The file does not exist at: " + filePath);

        page.locator("input#file-upload").setInputFiles(Paths.get(filePath));
        page.locator("input#file-submit").click();

        Locator uploadedFileName = page.locator("#uploaded-files");
        uploadedFileName.waitFor(new Locator.WaitForOptions().setTimeout(10000));

        String actualText = uploadedFileName.textContent().trim();
        System.out.println("📄 Uploaded file text from page: '" + actualText + "'");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("upload-result.png")));

        assertTrue(uploadedFileName.isVisible(), "Uploaded file name should be visible.");
        assertEquals("test-file.txt", actualText, "Uploaded file name should match the selected file.");

    }
}
