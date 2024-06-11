import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

/**
 * Test class for {@code CollectionOfSymbol}.
 */
public class CollectionOfSymbolTest {

    private Grammar mockGrammar;
    private Random mockRandom;
    private CollectionOfSymbol collectionOfSymbol;

    @BeforeEach
    void setUp() {
        // Setup mock Grammar and Random objects.
        mockGrammar = new Grammar(); // Assuming Grammar has a no-arg constructor.
        mockRandom = new Random(0); // Use a seed for reproducibility.


        collectionOfSymbol = new CollectionOfSymbol();
    }

    @Test
    @DisplayName("Test adding symbols to the sequence")
    void testAddToSequence() {
        Symbol symbol = new Terminal("test"); // Assuming Terminal is a valid Symbol implementation.
        collectionOfSymbol.addToSequence(symbol);

        // Assuming there's a way to get the sequence or its size, otherwise you would need to add it.
        assertEquals(1, collectionOfSymbol.getSequence().size(), "Sequence should contain one symbol.");
    }

    @Test
    void testGetToSequenceNull() {

        // Assuming there's a way to get the sequence or its size, otherwise you would need to add it.
        assertEquals(0, collectionOfSymbol.getSequence().size(), "Sequence should contain one symbol.");
    }

    @Test
    @DisplayName("Test generate method for valid output")
    void testGenerate() {
        // Add symbols to the collectionOfSymbol
        Symbol symbol1 = new Terminal("Hello");
        Symbol symbol2 = new Terminal(" ");
        Symbol symbol3 = new Terminal("World!");
        collectionOfSymbol.addToSequence(symbol1);
        collectionOfSymbol.addToSequence(symbol2);
        collectionOfSymbol.addToSequence(symbol3);

        // Generate the output
        String output = collectionOfSymbol.generate(mockGrammar, mockRandom);

        // Assuming the generate method concatenates symbol strings directly.
        assertEquals(" Hello   World!", output, "Generated string should match expected output.");
    }

    @Test
    void testEquals_Symmetry() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("a"));

        assertTrue(cos1.equals(cos2) && cos2.equals(cos1));
        assertEquals(cos1.hashCode(), cos2.hashCode());
    }

    @Test
    void testHashCode_Consistency() {
        CollectionOfSymbol cos = new CollectionOfSymbol();
        cos.addToSequence(new Terminal("a"));

        int expectedHashCode = cos.hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(expectedHashCode, cos.hashCode());
        }
    }

    @Test
    @DisplayName("Test equals method non-symmetrical")
    void testEqualsNonSymmetrical() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("b"));

        assertFalse(cos1.equals(cos2), "Collections with different sequences should not be equal.");
    }


    @Test
    @DisplayName("Test hashCode with different sequences")
    void testHashCodeWithDifferentSequences() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("b"));

        assertNotEquals(cos1.hashCode(), cos2.hashCode(), "Different sequences should have different hash codes.");
    }

    @Test
    @DisplayName("Test adding null to the sequence")
    void testAddNullToSequence() {
        assertThrows(NullPointerException.class, () -> {
            collectionOfSymbol.addToSequence(null);
        }, "Adding null to sequence should throw NullPointerException.");
    }

    @Test
    @DisplayName("Test generate with no applicable rules")
    void testGenerateWithNoApplicableRules() {
        // Setup mockGrammar to return an empty list or null for rules
        // ...

        String output = collectionOfSymbol.generate(mockGrammar, mockRandom);
        assertTrue(output.isEmpty(), "Generated string should be empty when no rules are applicable.");
    }



    @Test
    @DisplayName("Test equals with different sequences")
    void testEqualsWithDifferentSequences() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("b"));

        assertFalse(cos1.equals(cos2), "Different sequences should not be equal.");
    }

    @Test
    @DisplayName("Test equals with same sequences different instances")
    void testEqualsWithSameSequencesDifferentInstances() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("a"));

        assertTrue(cos1.equals(cos2), "Sequences with the same elements should be equal.");
    }

    @Test
    @DisplayName("Test equals with self")
    void testEqualsWithSelf() {
        CollectionOfSymbol cos = new CollectionOfSymbol();
        cos.addToSequence(new Terminal("a"));

        assertTrue(cos.equals(cos), "Sequence should be equal to itself.");
    }

    @Test
    @DisplayName("Test equals with null")
    void testEqualsWithNull() {
        CollectionOfSymbol cos = new CollectionOfSymbol();
        cos.addToSequence(new Terminal("a"));

        assertFalse(cos.equals(null), "Sequence should not be equal to null.");
    }

    @Test
    @DisplayName("Test equals with different object type")
    void testEqualsWithDifferentObjectType() {
        CollectionOfSymbol cos = new CollectionOfSymbol();
        cos.addToSequence(new Terminal("a"));

        Object other = new Object();

        assertFalse(cos.equals(other), "Sequence should not be equal to an object of a different type.");
    }

    @Test
    @DisplayName("Test hashCode with equal sequences")
    void testHashCodeWithEqualSequences() {
        CollectionOfSymbol cos1 = new CollectionOfSymbol();
        cos1.addToSequence(new Terminal("a"));

        CollectionOfSymbol cos2 = new CollectionOfSymbol();
        cos2.addToSequence(new Terminal("a"));

        assertEquals(cos1.hashCode(), cos2.hashCode(), "Equal sequences should have the same hash code.");
    }



}
