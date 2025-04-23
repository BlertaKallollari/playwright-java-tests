package api;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetPostsTest {
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
    public void shouldFetchAllPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        APIResponse response = page.request().get(url);

        assertEquals(200, response.status(), "Response status should be 200");

        String responseBody = new String(response.body());
        assertTrue(responseBody.contains("userId"), "Response should contain userId field");
    }

    @Test
    public void shouldFetchPostById() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        APIResponse response = page.request().get(url);

        assertEquals(200, response.status(), "Response status should be 200");

        String responseBody = new String(response.body());
        assertTrue(responseBody.contains("title"), "Response should contain title field");
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
