import io.github.darvil.utils.ModifyRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModifyRecordTest {

	@Test
	void testOf() {
		ModifyRecord<String> record = ModifyRecord.of("Test");
		assertEquals("Test", record.get());
		assertFalse(record.isModified());
	}

	@Test
	void testEmpty() {
		ModifyRecord<String> record = ModifyRecord.empty();
		assertNull(record.get());
		assertFalse(record.isModified());
	}

	@Test
	void testSet() {
		ModifyRecord<String> record = ModifyRecord.of("Test");
		record.set("New Value");
		assertEquals("New Value", record.get());
		assertTrue(record.isModified());
	}

	@Test
	void testSetIfNotModified() {
		ModifyRecord<String> record = ModifyRecord.of("Test");
		record.setIfNotModified("New Value");
		assertEquals("New Value", record.get());
		assertTrue(record.isModified());

		// Try to set again, should not change
		record.setIfNotModified("Another Value");
		assertEquals("New Value", record.get());
	}

	@Test
	void testIsModified() {
		ModifyRecord<String> record = ModifyRecord.of("Test");
		assertFalse(record.isModified());
		record.set("New Value");
		assertTrue(record.isModified());
	}
}