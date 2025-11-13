import io.github.darvil82.utils.LoopPool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoopPoolTest {

	@Test
	void testOf() {
		assertEquals("A", LoopPool.of("A", "B", "C").current());
		assertEquals("B", LoopPool.<String>of(1, "A", "B", "C").current());
	}

	@Test
	void testNext() {
		LoopPool<String> pool = LoopPool.of("A", "B", "C");
		assertEquals("A", pool.current());
		assertEquals("B", pool.next());
		assertEquals("C", pool.next());
		assertEquals("A", pool.next()); // Loop back to the start
	}

	@Test
	void testPrev() {
		LoopPool<String> pool = LoopPool.of("A", "B", "C");
		assertEquals("A", pool.current());
		assertEquals("C", pool.prev()); // Loop back to the end
		assertEquals("B", pool.prev());
		assertEquals("A", pool.prev());
	}

	@Test
	void testAt() {
		LoopPool<String> pool = LoopPool.of("A", "B", "C");
		assertEquals("A", pool.at(0));
		assertEquals("B", pool.at(1));
		assertEquals("C", pool.at(2));
		assertEquals("A", pool.at(3)); // Loop back to the start
	}
}