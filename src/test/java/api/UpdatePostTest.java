package api;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePostTest {
    static Playwright playwright;
    static APIRequestContext request;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext();
    }

    @AfterAll
    static void teardown() {
        request.dispose();
        playwright.close();
    }

    @Test
    void shouldUpdatePostSuccessfully() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("id", 1);
        updateData.put("title", "Updated Title");
        updateData.put("body", "Updated Body Content");
        updateData.put("userId", 1);

        APIResponse response = request.put("https://jsonplaceholder.typicode.com/posts/1",
                RequestOptions.create().setData(updateData));

        assertEquals(200, response.status(), "Expected status code 200 for PUT request");

        String responseBody = response.text();
        System.out.println("Response body: " + responseBody);

        assertTrue(responseBody.contains("Updated Title"), "Response should contain updated title");
    }
}
