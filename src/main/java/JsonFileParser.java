import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * This class provides functionality to parse a JSON file and convert its contents into
 * a Grammar object.
 */
public class JsonFileParser {

	private String fileName;
	private final int ZERO = 0;
	private final int ONE = 1;
	private final String BLANK = " ";
	private final String BRACKET_PATTERN = "<.*?>";

	/**
	 * Constructor for creating a JsonFileParser.
	 *
	 * @param fileName the name of the JSON file to be parsed.
	 */
	public JsonFileParser(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Parses the JSON file and constructs a Grammar object from its contents.
	 *
	 * @return a Grammar object representing the structure defined in the JSON file.
	 */
	public Grammar JsonFileParser() {
		Grammar gram = null;
		try {
			File jsonFile = new File(this.fileName);
			FileReader fileReader = new FileReader(jsonFile);
			JSONTokener jsonTokener = new JSONTokener(fileReader);
			gram = new Grammar();

			JSONObject jsonObject = new JSONObject(jsonTokener);

			for (String key : jsonObject.keySet()) {
				if (key.equals("grammarDesc")) {
					gram.grammarDesc = jsonObject.getString(key);
				} else if (key.equals("grammarTitle")) {
					gram.grammarName = jsonObject.getString(key);
				} else {
					JSONArray arr = jsonObject.getJSONArray(key);
					gram.addToRuleArray(parseRule(key, arr));
				}
			}
			return gram;
		} catch (Exception e) {
			CustomException.INVALID_INPUT.getMessage();
		}
		return gram;
	}

	/**
	 * Parses a JSON array corresponding to a rule and constructs a Rule object.
	 *
	 * @param key the name of the rule.
	 * @param arr the JSON array containing the rule definitions.
	 * @return a new Rule object representing the parsed rule.
	 */
	private Rule parseRule(String key, JSONArray arr) {
		Rule rule = new Rule(key);
		for (int i = ZERO; i < arr.length(); i++) {
			String s = arr.optString(i);
			String[] arr1 = s.split(BLANK);
			CollectionOfSymbol collection = new CollectionOfSymbol();
			for (int j = ZERO; j < arr1.length; j++) {
				collection.addToSequence(sortNoTermOrTerm(arr1[j]));
			}
			rule.addToArrayList(collection);
		}
		return rule;
	}

	/**
	 * Trims a string by removing the first and last character.
	 *
	 * @param s the string to be trimmed.
	 * @return the trimmed string.
	 */
  public String trimString(String s) {
		int temp = s.length() - ONE;
		s = s.substring(ONE, temp);
		return s;
	}

	/**
	 * Determines whether a string represents a terminal or a nonterminal symbol and creates the appropriate Symbol object.
	 *
	 * @param nextPattern the string to analyze.
	 * @return a Symbol object representing either a terminal or nonterminal.
	 */
	public Symbol sortNoTermOrTerm(String nextPattern) {
		Pattern regex = Pattern.compile(BRACKET_PATTERN);
		Matcher matcher = regex.matcher(nextPattern);
		Symbol name = null;
		if (matcher.find()) {
			String nonTerminalName = trimString(nextPattern);
			name = new NonterminalName(nonTerminalName);
		} else {
			String terminalName = nextPattern;
			name = new Terminal(terminalName);
		}
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof JsonFileParser that)) {
			return false;
		}
		return Objects.equals(fileName, that.fileName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fileName);
	}



}
