import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileParserTest {

    private JsonFileParser parser;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @TempDir
    Path tempDir; // JUnit Jupiter will take care of creating and deleting this temporary directory

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void parse_ValidJson_CreatesGrammarObject() throws IOException {
        // Given
        String validJson = "{ \"grammarTitle\": \"Test Grammar\", \"grammarDesc\": \"A description\", \"rule1\": [\"<rule2>\", \"text\"] }";
        File tempFile = createTempFile(validJson);

        // When
        parser = new JsonFileParser(tempFile.getAbsolutePath());
        Grammar result = parser.JsonFileParser(); // Method name should be parse() to follow Java conventions

        // Then
        assertNotNull(result);
        assertEquals("Test Grammar", result.getGrammarName());
        assertEquals("A description", result.getGrammarDesc());
        assertEquals(1, result.rules.size()); // Assuming we expect one rule here
    }

    @Test
    void parse_InvalidJson_ReturnsNull() throws IOException {
        // Given
        String path = "src/main/resources/a";

        // When
        parser = new JsonFileParser(path);
        Grammar result = parser.JsonFileParser();

        // Then
        assertNull(result); // We expect the method to return null if parsing fails
    }
    @Test
    public void parse_InvalidJson_PrintsErrorMessage() throws IOException {
        // Given
        String invalidJson = "invalid json";
        File tempFile = createTempFile(invalidJson);

        // When
        JsonFileParser parser = new JsonFileParser(tempFile.getAbsolutePath());
        parser.JsonFileParser();

        // Then
        assertTrue(outContent.toString().contains("Invalid input provided."));
    }



    public File createTempFile(String content) throws IOException {
        File tempFile = tempDir.resolve("tempJson.json").toFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write(content);
        writer.close();
        return tempFile;
    }


}
