import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAddition() {
        assertEquals(5, ArithmeticOperations.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(-1, ArithmeticOperations.subtract(2, 3));
    }

    @Test
    void testMultiplication() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(2.0, ArithmeticOperations.divide(6, 3));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(5, 0);
        });
    }
}