package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePostsTest {
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
    void shouldCreatePostSuccessfully() {
        APIRequestContext request = playwright.request().newContext();

        APIResponse response = request.post("https://jsonplaceholder.typicode.com/posts",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData("{\"title\": \"My test post\", \"body\": \"This is the body of the test post.\", \"userId\": 1}")
        );

        assertEquals(201, response.status());

        String responseBody = new String(response.body());
        System.out.println("Response body: " + responseBody);

        JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
        assertEquals("My test post", json.get("title").getAsString());
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}

