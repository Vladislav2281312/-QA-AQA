import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void testFactorialPositive() {
        assertEquals(120, FactorialCalculator.factorial(5));
        assertEquals(1, FactorialCalculator.factorial(0));
        assertEquals(1, FactorialCalculator.factorial(1));
        assertEquals(3628800, FactorialCalculator.factorial(10));
    }

    @Test
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.factorial(-3);
        });
    }
}