package api;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PatchPostTest {
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
    void shouldPartiallyUpdatePost() {
        Map<String, Object> patchData = new HashMap<>();
        patchData.put("title", "Partially Updated Title");

        APIResponse response = request.patch("https://jsonplaceholder.typicode.com/posts/1",
                RequestOptions.create().setData(patchData));

        assertEquals(200, response.status(), "Expected status code 200 for PATCH request");

        String responseBody = response.text();
        System.out.println("Response body: " + responseBody);

        assertTrue(responseBody.contains("Partially Updated Title"), "Response should contain updated title");
    }
}

