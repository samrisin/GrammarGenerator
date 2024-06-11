/**
 * This interface represents a symbol in a grammar.
 * A symbol can be either a terminal or a non-terminal in the context of generative grammars.
 */
public interface Symbol extends Generable {

    /**
     * Retrieves the name of the symbol.
     *
     * @return The name representing this symbol.
     */
    String getName();
}
