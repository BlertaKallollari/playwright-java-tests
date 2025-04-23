package api;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeletePostTest {
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
    void shouldDeletePost() {
        APIResponse response = request.delete("https://jsonplaceholder.typicode.com/posts/1");

        assertEquals(200, response.status(), "Expected status code 200 for DELETE request");

        String responseBody = response.text();
        System.out.println("Response body: " + responseBody);

        assertEquals("{}", responseBody.trim(), "Expected an empty object as response");
    }
}

