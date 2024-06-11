import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class NonterminalNameTest {

    private Grammar grammar;
    private Random random;
    private NonterminalName nonterminal;

    @BeforeEach
    void setUp() {
        grammar = new Grammar(); // Assuming a valid Grammar class
        random = new Random(0);  // Deterministic seeding for reproducibility
        nonterminal = new NonterminalName("S");
    }

    @Test
    void getName_ReturnsCorrectName() {
        assertEquals("S", nonterminal.getName(), "getName should return the correct non-terminal name.");
    }


    @Test
    void testEquals_Reflexivity() {
        assertTrue(nonterminal.equals(nonterminal), "equals should be reflexive.");
    }

    @Test
    void testEquals_Symmetry() {
        NonterminalName otherNonterminal = new NonterminalName("S");
        assertTrue(nonterminal.equals(otherNonterminal) && otherNonterminal.equals(nonterminal),
            "equals should be symmetric.");
    }

    @Test
    void testEquals_Transitivity() {
        NonterminalName nt1 = new NonterminalName("S");
        NonterminalName nt2 = new NonterminalName("S");
        NonterminalName nt3 = new NonterminalName("S");
        assertTrue(nt1.equals(nt2) && nt2.equals(nt3) && nt1.equals(nt3),
            "equals should be transitive.");
    }

    @Test
    void testEquals_Consistency() {
        NonterminalName otherNonterminal = new NonterminalName("S");
        for (int i = 0; i < 10; i++) {
            assertTrue(nonterminal.equals(otherNonterminal),
                "equals should be consistent across calls.");
        }
    }

    @Test
    void testEquals_Null() {
        assertFalse(nonterminal.equals(null), "equals should return false when comparing with null.");
    }

    @Test
    void testEquals_DifferentType() {
        Object otherType = new Object();
        assertFalse(nonterminal.equals(otherType), "equals should return false when comparing with a different type.");
    }

    @Test
    void testEquals_DifferentValue() {
        NonterminalName differentNonterminal = new NonterminalName("T");
        assertFalse(nonterminal.equals(differentNonterminal), "equals should return false for non-terminals with different names.");
    }

    @Test
    void testHashCode_Consistency() {
        int expectedHashCode = nonterminal.hashCode();
        assertEquals(expectedHashCode, nonterminal.hashCode(), "hashCode should be consistent across calls.");
    }

    @Test
    void testHashCode_Equality() {
        NonterminalName otherNonterminal = new NonterminalName("S");
        assertEquals(nonterminal.hashCode(), otherNonterminal.hashCode(), "hashCode should be equal for equal objects.");
    }
}
