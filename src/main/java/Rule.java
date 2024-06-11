import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents a rule in a grammar.
 * A rule is composed of a name and a sequence of symbol collections,
 * where each collection can be either terminal or non-terminal symbols.
 */
public class Rule implements Generable {

	/**
	 * The name of the rule.
	 */
	public String ruleName;

	/**
	 * A list of collections that represent the sequence of symbols in this rule.
	 */
	public ArrayList<CollectionOfSymbol> sequence;

	/**
	 * Constructs a new Rule with a given name.
	 *
	 * @param ruleName The name of the rule.
	 */
	public Rule(String ruleName) {
		this.ruleName = ruleName;
		sequence = new ArrayList<>();
	}

	/**
	 * Adds a collection of symbols (either terminal or non-terminal) to the rule's sequence.
	 *
	 * @param collection The collection of symbols to be added to the rule's sequence.
	 */
	public void addToArrayList(CollectionOfSymbol collection) {
		sequence.add(collection);
	}

	/**
	 * Retrieves the name of the rule.
	 *
	 * @return The name of the rule.
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Generates a string representation by randomly selecting one of the symbol collections
	 * in the sequence and then generating a string from it.
	 *
	 * @param grammar The context of the grammar to which this rule belongs.
	 * @param random  The random generator to select a random collection of symbols.
	 * @return The generated string from the selected collection of symbols.
	 */
	@Override
	public String generate(Grammar grammar, Random random) {
		int randomIndex = random.nextInt(sequence.size());
		return sequence.get(randomIndex).generate(grammar, random);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rule rule)) {
			return false;
		}
		return Objects.equals(getRuleName(), rule.getRuleName()) && Objects.equals(sequence, rule.sequence);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getRuleName(), sequence);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Rule{" +
				"ruleName='" + ruleName + '\'' +
				'}';
	}
}
