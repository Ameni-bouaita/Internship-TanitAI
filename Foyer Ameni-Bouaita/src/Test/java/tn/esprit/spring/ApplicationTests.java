package tn.esprit.spring;

import org.junit.jupiter.api.Test; // Import JUnit 5
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest // Ensure this annotation is present to load the Spring context
public class ApplicationTests {

    @Test
    void contextLoads() {
        // This test will simply check if the application context loads successfully
        assertTrue(true); // This can remain as is for now, but consider actual logic later
    }
}
