import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrammarTest {

    private Grammar grammar;
    private Rule ruleStart;
    private Random random;

    @BeforeEach
    void setUp() {
        grammar = new Grammar();
        ruleStart = new Rule("start") {
            @Override
            public String generate(Grammar grammar, Random random) {
                return "Generated String";
            }
        };
        random = new Random();

        grammar.grammarName = "Test Grammar";
        grammar.grammarDesc = "This is a test grammar.";
    }

    @Test
    void getGrammarName_ReturnsCorrectName() {
        assertEquals("Test Grammar", grammar.getGrammarName());
    }

    @Test
    void getGrammarDesc_ReturnsCorrectDescription() {
        assertEquals("This is a test grammar.", grammar.getGrammarDesc());
    }

    @Test
    void addToRuleArray_AddsRuleToGrammar() {
        int initialSize = grammar.rules.size();
        grammar.addToRuleArray(ruleStart);
        assertEquals(initialSize + 1, grammar.rules.size());
    }

    @Test
    void findRule_FindsCorrectRuleIndex() {
        grammar.addToRuleArray(ruleStart);
        int index = grammar.findRule(0, "start");
        assertEquals(0, index);
    }

    @Test
    void getRule_ReturnsCorrectRule() {
        grammar.addToRuleArray(ruleStart);
        Rule retrievedRule = grammar.getRule(0);
        assertEquals(ruleStart, retrievedRule);
    }

    @Test
    void generate_ReturnsCorrectString() {
        grammar.addToRuleArray(ruleStart);
        String result = grammar.generate(grammar, random);
        assertEquals("Generated String", result);
    }
    @Test
    public void testEquals_DifferentObjects_ReturnsFalse() {
        // Setup
        Grammar grammar1 = new Grammar();
        grammar1.grammarName = "TestGrammar1";
        grammar1.grammarDesc = "A test grammar 1";

        Grammar grammar2 = new Grammar();
        grammar2.grammarName = "TestGrammar2";
        grammar2.grammarDesc = "A test grammar 2";

        // Assertion
        assertFalse(grammar1.equals(grammar2));
    }

    @Test
    public void testEquals_SameObject_ReturnsTrue() {
        // Setup
        Grammar grammar = new Grammar();
        grammar.grammarName = "TestGrammar";
        grammar.grammarDesc = "A test grammar";

        // Assertion
        assertTrue(grammar.equals(grammar));
    }



    @Test
    public void testEquals_EqualObjects_ReturnsTrue() {
        // Setup
        Grammar grammar1 = new Grammar();
        grammar1.grammarName = "TestGrammar";
        grammar1.grammarDesc = "A test grammar";

        Grammar grammar2 = new Grammar();
        grammar2.grammarName = "TestGrammar";
        grammar2.grammarDesc = "A test grammar";

        // Assertion
        assertTrue(grammar1.equals(grammar2));
    }

    @Test
    public void testHashCode_EqualObjects_SameHashCode() {
        // Setup
        Grammar grammar1 = new Grammar();
        grammar1.grammarName = "TestGrammar";
        grammar1.grammarDesc = "A test grammar";

        Grammar grammar2 = new Grammar();
        grammar2.grammarName = "TestGrammar";
        grammar2.grammarDesc = "A test grammar";

        // Assertion
        assertEquals(grammar1.hashCode(), grammar2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects_DifferentHashCode() {
        // Setup
        Grammar grammar1 = new Grammar();
        grammar1.grammarName = "TestGrammar1";
        grammar1.grammarDesc = "A test grammar 1";

        Grammar grammar2 = new Grammar();
        grammar2.grammarName = "TestGrammar2";
        grammar2.grammarDesc = "A test grammar 2";

        // Assertion
        assertNotEquals(grammar1.hashCode(), grammar2.hashCode());
    }
    @Test
    void findRule_RuleDoesNotExist_ReturnsMinusOne() {
        grammar.addToRuleArray(ruleStart);
        int index = grammar.findRule(0, "nonexistent");
        assertEquals(-1, index);
    }

    @Test
    void generate_StartRuleDoesNotExist_ReturnsEmptyString() {
        // Assuming that an empty grammar will not generate anything.
        String result = grammar.generate(grammar, random);
        assertEquals("", result);
    }

    @Test
    void testEquals_NonGrammarObject_ReturnsFalse() {
        Object nonGrammarObject = new Object();
        assertFalse(grammar.equals(nonGrammarObject));
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {
        Grammar nullGrammar = null;
        assertFalse(grammar.equals(nullGrammar));
    }

}
