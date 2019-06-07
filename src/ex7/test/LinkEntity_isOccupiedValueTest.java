package ex7.test;

import org.junit.jupiter.api.Test;

import ex7.main.Snake;
import junit.framework.TestCase;

class LinkEntity_isOccupiedValueTest extends TestCase {

	@Test
	void test() {
		Snake snake = new Snake(2, 1, 1, 1);
		snake.setNext(new Snake(2, 2, 1, 1));
		boolean b = snake.isOccupied(2, 1);
		assertEquals(true, b);
	}
}