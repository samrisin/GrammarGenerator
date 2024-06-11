/**
 * An enumeration of custom exceptions, each with an associated message.
 */
public enum CustomException {

  /** Error for invalid input. */
  INVALID_INPUT("Invalid input provided."),

  /** Error when a file is not found. */
  FILE_NOT_FOUND("File not found."),

  /** Error for a non-existent Path. */
  PATH_INVALID("The path does not exist."),


  /** Error for a non-existent directory. */
  INVALID_DICTIONARY("The path exists but is not a directory.");



  private final String message;

  /**
   * Constructs a {@code CustomException} with the specified detail message.
   *
   * @param message the detail message pertaining to this error
   */
  CustomException(String message) {
    this.message = message;
  }

  /**
   * Returns the detail message of the exception.
   * @return message
   */
  public String getMessage() {
    System.out.println(message);
    return message;
  }
}
