import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
/**
 * The {@code GenerateProcessing} class is responsible for orchestrating the generation
 * of sentences from a set of grammars. It allows users to select a grammar and
 * generate sentences as specified by the grammar rules.
 */
public class GenerateProcessing {

  private String grammarFilePath;
  private List<Grammar> grammarList;
  private final int ZERO = 0;
  private final int ONE = 1;
  private final String BLANK = " ";
  private final String REG = "\\d+";

  /**
   * Constructs a {@code GenerateProcessing} instance with the specified grammar file path.
   *
   * @param grammarFilePath the path to the directory containing grammar JSON files
   */
  public GenerateProcessing(String grammarFilePath) {
    this.grammarFilePath = grammarFilePath;
    this.grammarList = new ArrayList<>();
  }

  /**
   * Prints the list of available grammars to the console.
   */
  public void printGrammarList() {
    System.out.println("The following grammars are available: ");
    int idx = ONE;
    for (Grammar grammar : grammarList) {
      System.out.println(idx++ + BLANK + grammar.getGrammarName());
    }
  }

  /**
   * Verifies the path and collects file names from the directory.
   *
   * @param grammarFilePath the path to the grammar files
   * @return {@code true} if the path is valid and JSON files are found, {@code false} otherwise
   */
  public boolean verifyPath(String grammarFilePath){
    Path path = Path.of(grammarFilePath);
    if (!Files.exists(path)) {
      CustomException.PATH_INVALID.getMessage();
      return false;
    } else if (!Files.isDirectory(path)) {
      CustomException.INVALID_DICTIONARY.getMessage();
      return false;
    }
    return true;
  }

  /**
   * Verifies the command line argument input by the user.
   *
   * @param command the scanner to read command line input
   * @return the command entered by the user
   */
  public String verifyCommandLine(Scanner command) {
    System.out.println("Which would you like to use? (q to quit)");
    while (true) {
      String textIn = command.nextLine();
      if (textIn.isEmpty()) {
        System.out.println("Command cannot be empty, please enter the number of grammar or 'q' to quit!");
        System.out.println("Please re-enter.");
      } else if ("q".equals(textIn)) {
        return textIn;
      } else {
        if (textIn.matches(REG) && Integer.parseInt(textIn) > ZERO && Integer.parseInt(textIn) <= grammarList.size()) {
          return textIn;
        }
        System.out.println("The command line must have a valid number for the above title.");
        System.out.println("Please re-enter.");
      }
    }
  }

  /**
   * Parses all JSON files within the specified directory path into Grammar objects.
   * It expects JSON files to be structured in a way that is compatible with the Grammar class.
   *
   * @param path The directory path where JSON files are located.
   */
  public void parseJsonFileToGrammar(Path path) {
    try {
      // Retrieve all JSON files from the directory
      List<Path> jsonFilesPath = Files.walk(path)
          .filter(Files::isRegularFile)
          .filter(filePath -> filePath.toString().endsWith(".json"))
          .collect(Collectors.toList());

      // Check if any JSON files were found
      if (jsonFilesPath.isEmpty()) {
        CustomException.FILE_NOT_FOUND.getMessage();
        return;
      }

      System.out.println(grammarFilePath);
      System.out.println("Loading grammars...");
      // Parse each JSON file into a Grammar object and add it to the grammar list
      for (Path filePath : jsonFilesPath) {
        JsonFileParser fp = new JsonFileParser(filePath.toString());
        Grammar grammar = fp.JsonFileParser();
        grammarList.add(grammar);
      }
    } catch (Exception e) {
      // If an error occurs during parsing, print the custom exception message
      CustomException.INVALID_INPUT.getMessage();
    }
  }

  /**
   * Generates sentences from the selected grammar based on user input and repeats
   * this process based on the user's desire to continue generating sentences.
   *
   * @param vertifyCommond A string input representing the selected grammar number.
   * @param command A Scanner object to read user input from the console.
   */
  public void GererateSentence(String vertifyCommond, Scanner command) {
    // Convert the verified command to an integer to select the grammar
    int grammarNumber = Integer.parseInt(vertifyCommond);
    Random random = new Random();

    // Generate a sentence using the selected grammar
    String sentence = grammarList.get(grammarNumber - ONE).generate(grammarList.get(grammarNumber - ONE), random);
    System.out.println(sentence);

    // Continue to offer sentence generation until the user decides to stop
    while (true) {
      System.out.println("Would you like another? (y/n)");
      String choice = command.nextLine();
      if (choice.equals("y")) {
        // If the user chooses 'y', generate another sentence
        System.out.println(grammarList.get(grammarNumber - ONE).generate(grammarList.get(grammarNumber - ONE), random));
      } else if (choice.equals("n")) {
        // If the user chooses 'n', exit the loop
        break;
      } else {
        // If the user inputs something other than 'y' or 'n', prompt again
        System.out.println("Please input (y/n), try again!");
      }
    }
  }


  /**
   * Initiates the process of listing available grammars and generating sentences.
   *
   */
  public void GenerateProcess() {
    if(!verifyPath(this.grammarFilePath)){
      return;
    }
    parseJsonFileToGrammar(Path.of(this.grammarFilePath));
    Scanner command = new Scanner(System.in);
    while(true){
      printGrammarList();
      String vertifyCommond = verifyCommandLine(command);
      if(vertifyCommond.equals("q")) {
        System.out.println("The system is exiting!");
        break;
      }else{
        GererateSentence(vertifyCommond,command);
      }
    }
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GenerateProcessing that)) {
      return false;
    }
    return Objects.equals(grammarFilePath, that.grammarFilePath)
        && Objects.equals(grammarList, that.grammarList);
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(grammarFilePath, grammarList);
  }
  /**
   * Get grammarList
   * @return grammarList
   */
  public List<Grammar> getGrammarList() {
    return grammarList;
  }
}
