import io.github.darvil82.utils.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {
	@Test
	public void testSimpleRange() {
		var range = Range.of(10);

		assertFalse(!range.isSimple());
		assertFalse(range.isInfinite());
		assertEquals(10, range.start());
		assertEquals(10, range.end());
	}

	@Test
	public void testRange() {
		var range = Range.from(10).to(25);

		assertEquals(10, range.start());
		assertEquals(25, range.end());
		assertFalse(range.isInfinite());
	}

	@Test
	public void testInfiniteRange() {
		var range = Range.from(10).toInfinity();

		assertEquals(10, range.start());
		assertEquals(Integer.MAX_VALUE, range.end());
		assertTrue(range.isInfinite());
	}

	@Test
	public void testInvalidRange() {
		assertThrows(IllegalArgumentException.class, () -> Range.from(10).to(-1));
		assertThrows(IllegalArgumentException.class, () -> Range.from(10).to(5));
		assertThrows(IllegalArgumentException.class, () -> Range.from(-4).to(5));
	}

	@Test
	public void testZeroRange() {
		var range = Range.NONE;

		assertFalse(!range.isSimple());
		assertTrue(range.isZero());
	}

	@Test
	public void testContains() {
		var range = Range.from(10).to(25);

		assertTrue(range.containsInclusive(10));
		assertFalse(range.containsExclusive(10));
		assertTrue(range.containsInclusive(25));
		assertFalse(range.containsExclusive(25));
		assertTrue(range.containsInclusive(15));
	}

	@Test
	public void testToArray() {
		{
			var range = Range.from(10).to(25);

			assertArrayEquals(new int[] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, range.toArray());
			assertArrayEquals(new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 }, range.toArray(false, false));
		}

		{
			var range = Range.of(5);

			assertArrayEquals(new int[] { 5 }, range.toArray());
			assertArrayEquals(new int[] { 5 }, range.toArray(false, false));
		}

		assertThrows(UnsupportedOperationException.class, () -> Range.ANY.toArray());
	}

	@Test
	public void testIterator() {
		{
			var range = Range.from(10).to(25);
			var iterator = range.iterator();

			for (int i = 10; i <= 25; i++) {
				assertTrue(iterator.hasNext());
				assertEquals(i, iterator.next());
			}

			assertFalse(iterator.hasNext());
		}

		{
			var range = Range.of(5);
			var iterator = range.iterator();

			assertTrue(iterator.hasNext());
			assertEquals(5, iterator.next());
			assertFalse(iterator.hasNext());
		}
	}
}