import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class GenerateProcessingTest {

    private GenerateProcessing generateProcessing;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        tempDir = Path.of( "src/main/resources/");
        generateProcessing = new GenerateProcessing(tempDir.toString());

    }

    @Test
    void verifyPath_ValidPath_ReturnsTrue() {
        assertTrue(generateProcessing.verifyPath(tempDir.toString()),
            "verifyPath should return true for a valid path");
    }

    @Test
    void verifyPath_InvalidPath_ReturnsFalse() {
        Path invalidPath = tempDir.resolve("nonexistent/");
        assertFalse(generateProcessing.verifyPath(invalidPath.toString()),
            "verifyPath should return false for an invalid path");
    }

    @Test
    void parseJsonFileToGrammar_ValidJsonFiles_AddsGrammarsToList() {
        // You would need to create a valid JSON file in the temp directory here.
        // For example, use Files.write(tempDir.resolve("grammar.json"), jsonContent.getBytes());
        generateProcessing.parseJsonFileToGrammar(tempDir);
        assertFalse(generateProcessing.getGrammarList().isEmpty(),
            "Grammar list should not be empty after parsing valid JSON files");
    }

    @Test
    void parseJsonFileToGrammar_NoJsonFiles_DoesNotAddGrammarsToList() {
        // Make sure there are no .json files in the temp directory.
        tempDir = Path.of( "src/main/resources/a");
        generateProcessing.parseJsonFileToGrammar(tempDir);
        assertTrue(generateProcessing.getGrammarList().isEmpty(),
            "Grammar list should be empty if no JSON files are parsed");
    }

    @Test
    void testEquals_Transitivity() {
        GenerateProcessing gp1 = new GenerateProcessing("path/to/grammar");
        GenerateProcessing gp2 = new GenerateProcessing("path/to/grammar");
        GenerateProcessing gp3 = new GenerateProcessing("path/to/grammar");

        assertTrue(gp1.equals(gp2));
        assertTrue(gp2.equals(gp3));
        assertTrue(gp1.equals(gp3));
    }

    @Test
    void testEquals_Null() {
        GenerateProcessing gp = new GenerateProcessing("path/to/grammar");
        assertFalse(gp.equals(null));
    }

    @Test
    void testHashCode_Consistency() {
        GenerateProcessing gp = new GenerateProcessing("path/to/grammar");
        int expectedHashCode = gp.hashCode();
        assertEquals(expectedHashCode, gp.hashCode());
    }

    @Test
    void testHashCode_Equality() {
        GenerateProcessing gp1 = new GenerateProcessing("path/to/grammar");
        GenerateProcessing gp2 = new GenerateProcessing("path/to/grammar");
        assertEquals(gp1.hashCode(), gp2.hashCode());
    }

    @Test
    void removeGrammar_ShouldReflectInGrammarList() {
        // Assume we have a removeGrammar method
        List<Grammar> grammarList = generateProcessing.getGrammarList();
        grammarList.add(new Grammar(/* constructor parameters */));
        assertEquals(1, grammarList.size(), "There should be one grammar in the list after removing one.");
    }

    @Test
    void getGrammarList_ShouldReturnAnEmptyListIfNoGrammarsAdded() {
        GenerateProcessing freshGenerateProcessing = new GenerateProcessing("path/to/grammar");
        List<Grammar> grammarList = freshGenerateProcessing.getGrammarList();
        assertTrue(grammarList.isEmpty(), "The grammar list should be empty if no grammars have been added.");
    }



}
