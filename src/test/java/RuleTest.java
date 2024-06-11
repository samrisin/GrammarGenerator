import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

class RuleTest {

    private Rule rule;
    private Random stubRandom;
    private Grammar stubGrammar;

    @BeforeEach
    void setUp() {
        rule = new Rule("TestRule");
        stubRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 0; // Always return the first element for predictability
            }
        };
        stubGrammar = new Grammar(); // Assume Grammar is a properly implemented class
    }

    @Test
    void constructor_AssignsNameAndInitializesSequence() {
        assertEquals("TestRule", rule.getRuleName());
        assertNotNull(rule.sequence);
        assertTrue(rule.sequence.isEmpty());
    }



    @Test
    void addToArrayList_AddsCollectionToSequence() {
        CollectionOfSymbol collection = new CollectionOfSymbol();
        rule.addToArrayList(collection);
        assertFalse(rule.sequence.isEmpty());
        assertEquals(1, rule.sequence.size());
        assertSame(collection, rule.sequence.get(0));
    }

    @Test
    void generate_ReturnsCorrectStringFromSequence() {
        CollectionOfSymbol collection = new CollectionOfSymbol() {
            @Override
            public String generate(Grammar grammar, Random random) {
                return "generatedString";
            }
        };
        rule.addToArrayList(collection);
        String result = rule.generate(stubGrammar, stubRandom);
        assertEquals("generatedString", result);
    }

    @Test
    void equals_DifferentRuleNames_ReturnsFalse() {
        Rule anotherRule = new Rule("AnotherTestRule");
        assertNotEquals(rule, anotherRule);
    }

    @Test
    void equals_SameRuleNameAndSequence_ReturnsTrue() {
        Rule anotherRule = new Rule("TestRule");
        assertEquals(rule, anotherRule);
    }

    @Test
    void hashCode_EqualsForSameRule() {
        Rule anotherRule = new Rule("TestRule");
        assertEquals(rule.hashCode(), anotherRule.hashCode());
    }

    @Test
    void hashCode_NotEqualsForDifferentRules() {
        Rule anotherRule = new Rule("AnotherTestRule");
        assertNotEquals(rule.hashCode(), anotherRule.hashCode());
    }


    @Test
    public void testEquals_DifferentObjectsSameNameAndSequence_ReturnsTrue() {
        Rule rule1 = new Rule("S");
        Rule rule2 = new Rule("S");

        // Assuming CollectionOfSymbol is another class that needs to be properly equal as well
        CollectionOfSymbol collection = new CollectionOfSymbol(); // You will need to create instances
        rule1.addToArrayList(collection);
        rule2.addToArrayList(collection);

        assertTrue(rule1.equals(rule2));
    }
    @Test
    public void testEquals_NullObject_ReturnsFalse() {
        Rule rule = new Rule("S");
        assertFalse(rule.equals(null));
    }

    @Test
    public void testEquals_SameObject_ReturnsTrue() {
        Rule rule = new Rule("S");
        assertTrue(rule.equals(rule));
    }


    @Test
    public void testEquals_DifferentClass_ReturnsFalse() {
        Rule rule = new Rule("S");
        Object other = new Object();
        assertFalse(rule.equals(other));
    }


    @Test
    public void testEquals_DifferentObjectsDifferentName_ReturnsFalse() {
        Rule rule1 = new Rule("S");
        Rule rule2 = new Rule("A");

        assertFalse(rule1.equals(rule2));
    }
    @Test
    public void testHashCode_EqualObjectsDifferentName_DifferentHashCode() {
        Rule rule1 = new Rule("S");
        Rule rule2 = new Rule("A");

        assertNotEquals(rule1.hashCode(), rule2.hashCode());
    }

    @Test
    void constructorShouldInitializeNameAndSequence() {
        // Arrange
        String expectedName = "MyRule";

        // Act
        Rule rule = new Rule(expectedName);

        // Assert
        assertNotNull(rule.sequence, "Sequence should be initialized.");
        assertTrue(rule.sequence.isEmpty(), "Sequence should be empty.");
        assertEquals(expectedName, rule.getRuleName(), "Rule name should match the expected name.");
    }


}
