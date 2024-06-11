import java.util.Objects;
import java.util.Random;

/**
 * A {@code Terminal} represents a terminal symbol in a grammar.
 */
public class Terminal implements Symbol {

	private final String variableName; // Name representing the terminal
	private final String BLANK = " ";

	/**
	 * Constructs a new {@code Terminal} with the given name.
	 *
	 * @param name the name representing the terminal
	 */
	public Terminal(String name) {
		this.variableName = name;
	}

	/**
	 * Returns the name of this terminal.
	 *
	 * @return the name of the terminal
	 */
	@Override
	public String getName() {
		return variableName;
	}

	/**
	 * Generates a string representation of this terminal.
	 *
	 * @param grammar the grammar context to use during generation
	 * @param random the random number generator to use for selection
	 * @return a string representation of the terminal
	 */
	@Override
	public String generate(Grammar grammar, Random random) {
		return BLANK + getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Terminal)) {
			return false;
		}
		Terminal terminal = (Terminal) o;
		return Objects.equals(variableName, terminal.variableName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(variableName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Terminal{" +
				"variableName='" + variableName + '\'' +
				", BLANK='" + BLANK + '\'' +
				'}';
	}
}
