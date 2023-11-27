import org.junit.jupiter.api.Test;
import utils.UtlString;

import static org.junit.jupiter.api.Assertions.*;

class UtlStringTest {

	@Test
	void testGetLongestLine() {
		String str = "Hello\nWorld\nThis is a long line\nshort";
		assertEquals("This is a long line", UtlString.getLongestLine(str));
	}

	@Test
	void testAllCharsMatch() {
		assertTrue(UtlString.allCharsMatch("Hello", Character::isLetter));
		assertTrue(UtlString.allCharsMatch("1221574", Character::isDigit));
		assertFalse(UtlString.allCharsMatch("Hello ", Character::isLetter));
	}

	@Test
	void testRequireValidName() {
		assertThrows(IllegalArgumentException.class, () -> UtlString.requireValidName("1Invalid"));
		assertDoesNotThrow(() -> UtlString.requireValidName("va--l12id"));
		assertDoesNotThrow(() -> UtlString.requireValidName("Valid_1"));
	}

	@Test
	void testWrap() {
		String str = "This is a long line that should be wrapped";
		String wrapped = UtlString.wrap(str, 10);
		assertEquals(4, wrapped.split("\n").length);
	}

	@Test
	void testIndent() {
		String str = "Hello\nWorld";
		String indented = UtlString.indent(str, 2);
		assertEquals("  Hello\n  World", indented);
	}

	@Test
	void testCenter() {
		assertEquals("-----Hello-----", UtlString.center("Hello", 15));
		assertEquals("###Hello###", UtlString.center("Hello", 10, '#'));
	}

	@Test
	void testRemoveSequences() {
		String str = "\u001b[31mHello\u001b[0m";
		assertEquals("Hello", UtlString.removeSequences(str));
	}

	@Test
	void testPlural() {
		assertEquals("1 apple", UtlString.plural("apple", 1));
		assertEquals("2 apples", UtlString.plural("apple", 2));
		assertEquals("0 apples", UtlString.plural("apple", 0));
	}

	@Test
	void testIsNullOrEmpty() {
		assertTrue(UtlString.isNullOrEmpty(null));
		assertTrue(UtlString.isNullOrEmpty(""));
		assertFalse(UtlString.isNullOrEmpty("Hello"));
	}

	@Test
	void testSplit() {
		String str = "Hello, World";
		String[] split = UtlString.split(str, ',');
		assertEquals(2, split.length);
		assertEquals("Hello", split[0]);
		assertEquals("World", split[1]);
	}

	@Test
	void testSplitAtLeadingWhitespace() {
		String str = "   Hello";
		var pair = UtlString.splitAtLeadingWhitespace(str);
		assertEquals("   ", pair.first());
		assertEquals("Hello", pair.second());
	}

	@Test
	void testEscapeQuotes() {
		String str = "He said, \"Hello\"";
		assertEquals("He said, \\\"Hello\\\"", UtlString.escapeQuotes(str));
	}
}