import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * A {@code CollectionOfSymbol} represents a sequence of grammar symbols, which can be terminals
 * or non-terminals.
 */
public class CollectionOfSymbol implements Generable {

	private  ArrayList<Symbol> sequence; // Sequence of symbols in the collection

	/** Constructs a new empty {@code CollectionOfSymbol}. */
	public CollectionOfSymbol() {
		sequence = new ArrayList<>();
	}

	/**
	 * Adds a symbol to the sequence.
	 *
	 * @param sym the {@code Symbol} to add to the sequence
	 */
	public void addToSequence(Symbol sym) {
		sequence.add(Objects.requireNonNull(sym, "Symbol cannot be null"));
	}

	/**
	 * Generates a concatenated string representation of the symbols in the sequence, based on
	 * a provided grammar and random seed.
	 *
	 * @param grammar the {@code Grammar} context to use for generation
	 * @param random the {@code Random} instance to use for any randomness in generation
	 * @return the generated string from the sequence of symbols
	 */
	@Override
	public String generate(Grammar grammar, Random random) {
		StringBuilder sentenceBuilder = new StringBuilder();
		for (Symbol symbol : sequence) {
			sentenceBuilder.append(symbol.generate(grammar, random));
		}
		return sentenceBuilder.toString();
	}
	/**
	 * get sequence
	 * @return sequence
	 */

	public ArrayList<Symbol> getSequence() {
		return sequence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CollectionOfSymbol)) {
			return false;
		}
		CollectionOfSymbol that = (CollectionOfSymbol) o;
		return Objects.equals(sequence, that.sequence);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(sequence);
	}
}
