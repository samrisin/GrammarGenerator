
/**
 * This class contains the main entry point for the Random Sentence Generation System.
 * It initializes the generation process using a predefined file path.
 */
public class RodomSentenceGenerateSystem {

	// The path to the resources folder where grammar files are expected to be located.
	private static final String path = "src/main/resources/";

	private static final int ONE = 1;
	/**
	 * The main method to start the Random Sentence Generation System.
	 *
	 * @param args The command-line arguments provided to the program.
	 */
	public static void main(String[] args)  {
		//sending the path + directory name
		if(args.length != ONE){
			CustomException.INVALID_INPUT.getMessage();
		}else{
			new GenerateProcessing(path+args[0]).GenerateProcess();
		}

	}
}
