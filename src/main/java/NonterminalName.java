import java.util.Objects;
import java.util.Random;

/**
 * This class represents a non-terminal symbol in a context-free grammar.
 * A non-terminal symbol is a symbol that can be expanded into other symbols
 * according to the rules of the grammar.
 */
public class NonterminalName implements Symbol {

	private String nonTerminalName;
	private final int ZERO = 0;

	/**
	 * Constructs a NonterminalName object with the specified name.
	 *
	 * @param name The name of the non-terminal symbol.
	 */
	public NonterminalName(String name) {
		this.nonTerminalName = name;
	}

	/**
	 * Retrieves the name of this non-terminal symbol.
	 *
	 * @return The name of the non-terminal symbol.
	 */
	@Override
	public String getName() {
		return this.nonTerminalName;
	}

	/**
	 * Generates a string representation for this non-terminal symbol by recursively
	 * expanding it according to the grammar's rules.
	 *
	 * @param grammar The grammar which contains the rules for expansion.
	 * @param random  The random number generator used to select amongst multiple rules.
	 * @return The generated string after expansion.
	 */
	@Override
	public String generate(Grammar grammar, Random random) {
		int ruleIndex = grammar.findRule(ZERO, getName());
		return grammar.getRule(ruleIndex).generate(grammar, random);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof NonterminalName that)) {
			return false;
		}
		return Objects.equals(nonTerminalName, that.nonTerminalName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nonTerminalName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "NonterminalName{" +
				"nonTerminalName='" + nonTerminalName + '\'' +
				", ZERO=" + ZERO +
				'}';
	}
}
