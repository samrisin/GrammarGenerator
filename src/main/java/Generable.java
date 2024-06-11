import java.util.Random;

/**
 * The {@code Generable} interface should be implemented by classes that represent grammar blocks
 * capable of generating strings based on certain rules or patterns.
 */
public interface Generable {

    /**
     * Generates a string based on the rules or patterns defined in the implementing class,
     * utilizing the specified grammar and randomness source.
     *
     * @param grammar the {@code Grammar} instance that provides the context for generation
     * @param random the {@code Random} instance to use for any randomization required in generation
     * @return a {@code String} generated according to the grammar and randomness provided
     */
    String generate(Grammar grammar, Random random);
}
