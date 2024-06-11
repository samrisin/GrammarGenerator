import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RodomSentenceGenerateSystemTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void mainWithValidInput() {
    String input = "1\nn\nq\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    String[] args1 = {"grammar"};
    Assertions.assertDoesNotThrow(() -> {
      RodomSentenceGenerateSystem.main(args1);
    });
  }

  @Test
  void mainWithMultipleValidInput() {
    String input = "1\ny\nn\nq\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    String[] args1 = {"grammar"};
    Assertions.assertDoesNotThrow(() -> {
      RodomSentenceGenerateSystem.main(args1);
    });
  }

  @Test
  public void testPathDoesNotExist() {
    // Assuming that args[0] would be the path, and we pass an invalid path.
    String[] args = {"nonexistent_path"};
    RodomSentenceGenerateSystem.main(args);

    // Verify that the message contains a specific text indicating the path does not exist
    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("The path does not exist"));
  }






}