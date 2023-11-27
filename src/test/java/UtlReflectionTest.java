import org.junit.jupiter.api.Test;
import utils.UtlReflection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtlReflectionTest {

	@Test
	public void testGetSimpleName() {
		assertEquals("Object", UtlReflection.getSimpleName(new Object() {}.getClass()));
	}

	@Test
	public void testInstantiate() {
		{
			var instance = UtlReflection.instantiate(TestClass.class);
			assertEquals(0xCafeBabe, instance.a);
			assertEquals(0xCafeBabe, instance.b);
		}

		{
			var instance = UtlReflection.instantiate(TestClass.class, List.of(0xDeadBeef));
			assertEquals(0xDeadBeef, instance.a);
			assertEquals(0xCafeBabe, instance.b);
		}

		{
			var instance = UtlReflection.instantiate(TestClass.class, List.of(0xDeadBeef, 0xDeadBeef));
			assertEquals(0xDeadBeef, instance.a);
			assertEquals(0xDeadBeef, instance.b);
		}
	}

	@Test
	public void testHasParameters() {
		var methods = List.of(TestClass.class.getDeclaredMethods());

		assertTrue(UtlReflection.hasParameters(methods.get(2)));
		assertTrue(UtlReflection.hasParameters(methods.get(1), Integer.class));
		assertTrue(UtlReflection.hasParameters(methods.get(0), Integer.class, Integer.class));
	}


	public static class TestClass {
		public int a, b;

		public TestClass() {
			this(0xCafeBabe);
		}

		public TestClass(Integer a) {
			this(a, 0xCafeBabe);
		}

		public TestClass(Integer a, Integer b) {
			this.a = a;
			this.b = b;
		}

		public void testMethod() {}
		public void testMethod(Integer a) {}
		public void testMethod(Integer a, Integer b) {}
	}
}