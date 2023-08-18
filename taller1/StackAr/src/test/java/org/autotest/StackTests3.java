package org.autotest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests3 extends MutationAnalysisRunner {
    @Override
    protected boolean useVerboseMode() {
        return false;
    }

    public void testSizeIncreasesByOne() throws Exception {
        Stack stack = createStack();
        assertEquals(0, stack.size());
        stack.push(42);
        assertEquals(1, stack.size());
    }

    public void testDefaultConstructor() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
    }

    public void testConstructorWithSpecifiedCapacity() throws Exception {
        Stack stack = createStack(5);
    }

    public void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            Stack stack = createStack(-1);
        });
    }

    public void testIsEmptyMethod() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    public void testIsFullMethod() throws Exception {
        Stack stack = createStack(1);
        assertFalse(stack.isFull());
        stack.push(42);
        assertTrue(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

    public void testToStringMethod() throws Exception {
        Stack stack = createStack(2);
        assertEquals("[]", stack.toString());
        stack.push(42);
        assertEquals("[42]", stack.toString());
        stack.push(43);
        assertEquals("[42,43]", stack.toString());
    }

    /* Tests propios */
    public void testCapacityIsOne() throws Exception {
        Stack stack = createStack(1);
    }

    public void testCapacityIsZeroShouldBeFullAndEmpty() throws Exception {
        Stack stack = createStack(0);
        assertTrue(stack.isEmpty());
        assertTrue(stack.isFull());
    }

    public void testCantPushToFullStack() throws Exception {
        Stack stack = createStack(1);
        stack.push(42);
        assertTrue(stack.isFull());
        assertThrows(IllegalStateException.class, () -> {
            stack.push(43);
        });
    }

    public void testPopReturnsLastElement() throws Exception {
        Stack stack = createStack(1);
        stack.push(42);
        int element = (int) stack.pop();
        assertEquals(42, element);
    }

    public void testPopFailsWhenEmpty() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    public void testDefaultCapacityIsTen() throws Exception {
        Stack stack = createStack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertTrue(stack.isFull());
    }

    public void testTopReturnsTopElement() throws Exception {
        Stack stack = createStack();
        stack.push(42);
        assertEquals(42, stack.top());
    }

    public void testTopFailsWhenEmpty() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        assertThrows(IllegalStateException.class, () -> {
            stack.top();
        });
    }

    public void testEmptyStackHashCode() throws Exception {
        Stack stack = createStack(0);
        int expectedHashCode = 31 + Arrays.hashCode(new Object[0]);
        // readIndex is -1
        expectedHashCode = 31 * expectedHashCode - 1;
        assertEquals(expectedHashCode, stack.hashCode());
    }

    public void testStackIsEqualToItself() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.equals(stack));
    }

    public void testStackIsNotNull() throws Exception {
        Stack stack = createStack();
        assertFalse(stack.equals(null));
    }

    public void testStackIsNotAString() throws Exception {
        Stack stack = createStack();
        assertFalse(stack.equals("stack"));
    }

    public void testStackIsEqualToAnotherEmptyStackWithSameCapacity() throws Exception {
        Stack stack = createStack(5);
        Stack anotherStack = createStack(5);
        assertTrue(stack.equals(anotherStack));
    }

    public void testStackIsNotEqualToStackWithOtherElements() throws Exception {
        Stack stack = createStack(5);
        Stack anotherStack = createStack(5);
        stack.push(42);
        anotherStack.push(43);
        assertFalse(stack.equals(anotherStack));
    }
}
