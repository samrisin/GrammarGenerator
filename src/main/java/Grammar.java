import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a Grammar which is a collection of production rules.
 * These rules define how strings in the language can be generated.
 */
public class Grammar implements Generable {

	/**
	 *List of production rules that define the grammar
	 */
	public ArrayList<Rule> rules;

	/**
	 *A human-readable name for the grammar
	 */
	public String grammarName;

	/**
	 * A description of the grammar's purpose or how it is to be used.
	 * This can provide context for anyone reading the code or using the grammar
	 * in their own projects.
	 */
	public String grammarDesc;

	// Constants for internal use
	private final String NONESTRING = "";
	private final String START = "start"; // The starting rule name for generation
	private final int ONE = 1;
	/**
	 * Retrieves the name of the grammar.
	 *
	 * @return The name assigned to the grammar.
	 */
	public String getGrammarName() {
		return grammarName;
	}

	/**
	 * Retrieves a human-readable description of the grammar.
	 *
	 * @return A description of the grammar.
	 */
	public String getGrammarDesc() {
		return grammarDesc;
	}

	/**
	 * Constructs a new, empty Grammar.
	 */
	public Grammar() {
		rules = new ArrayList<>();
	}

	/**
	 * Adds a new production rule to the grammar.
	 *
	 * @param rule The production rule to add.
	 */
	public void addToRuleArray(Rule rule) {
		rules.add(rule);
	}

	/**
	 * Finds a rule by name starting from a given index.
	 * It is used internally to locate rules when generating text.
	 *
	 * @param i The index from which to start the search.
	 * @param ruleName The name of the rule to find.
	 * @return The index in the rules list where the named rule is found.
	 */
	public int findRule(int i, String ruleName) {
		while (i < rules.size() && !rules.get(i).getRuleName().equals(ruleName)) {
			i++;
		}
		return i < rules.size() ? i : -ONE; // Return -1 if rule is not found
	}

	/**
	 * Retrieves a rule from the grammar by its index.
	 *
	 * @param rulePosition The index of the rule in the list.
	 * @return The Rule object at the given index.
	 */
	public Rule getRule(int rulePosition) {
		return rules.get(rulePosition);
	}

	/**
	 * Generates a string using the grammar rules starting from the rule named "start".
	 *
	 * @param grammar The Grammar object (itself) to be used for generation.
	 * @param random A Random object to introduce randomness into the generation process.
	 * @return The generated string following the grammar rules.
	 */
	@Override
	public String generate(Grammar grammar, Random random) {
		String s = NONESTRING;
		for (Rule rule : rules) {
			if (rule.getRuleName().equals(START)) {
				s += rule.generate(grammar, random);
				break; // Stop after finding and using the start rule
			}
		}
		return s.trim(); // Trim in case extra whitespace was added during generation
	}

	/**
	 * Indicates whether some other object is "equal to" this Grammar.
	 * Grammars are considered equal if they have the same rules, name, and description.
	 *
	 * @param o The reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Grammar grammar)) {
			return false;
		}
		return Objects.equals(rules, grammar.rules) &&
				Objects.equals(grammarName, grammar.grammarName) &&
				Objects.equals(grammarDesc, grammar.grammarDesc);
	}

	/**
	 * Returns a hash code value for the object.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(rules, grammarName, grammarDesc);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Grammar{" +
				"rules=" + rules +
				", grammarName='" + grammarName + '\'' +
				", grammarDesc='" + grammarDesc + '\'' +
				", NONESTRING='" + NONESTRING + '\'' +
				", START='" + START + '\'' +
				", ONE=" + ONE +
				'}';
	}
}
