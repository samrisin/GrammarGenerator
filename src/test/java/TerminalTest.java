import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {

    private Terminal terminal;
    private Grammar mockGrammar; // You may need to create a mock Grammar object if generate() depends on it
    private Random mockRandom;   // You can use a mock or a seeded Random object for predictability

    @BeforeEach
    void setUp() {
        terminal = new Terminal("test");
        mockRandom = new Random(0); // Seeded for predictability in tests
    }

    @Test
    void testGetName() {
        assertEquals("test", terminal.getName());
    }

    @Test
    void testGenerate() {
        // Mocking the behavior of the grammar if necessary
        // Mockito.when(mockGrammar.someMethod()).thenReturn(someValue);
        String generated = terminal.generate(mockGrammar, mockRandom);
        assertEquals("test", generated.trim());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(terminal, terminal);
    }

    @Test
    void testEquals_SameValues() {
        Terminal sameTerminal = new Terminal("test");
        assertEquals(terminal, sameTerminal);
    }

    @Test
    void testEquals_DifferentValues() {
        Terminal differentTerminal = new Terminal("different");
        assertNotEquals(terminal, differentTerminal);
    }

    @Test
    void testEquals_DifferentType() {
        assertNotEquals(terminal, new Object());
    }

    @Test
    void testEquals_Null() {
        assertNotEquals(terminal, null);
    }

    @Test
    void testHashCode() {
        Terminal sameTerminal = new Terminal("test");
        assertEquals(terminal.hashCode(), sameTerminal.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Terminal{variableName='test', BLANK=' '}";
        assertEquals(expected, terminal.toString());
    }
}
