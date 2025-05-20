import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testFirstGreater() {
        assertEquals("Первое число больше второго", NumberComparator.compare(5, 3));
    }

    @Test
    void testSecondGreater() {
        assertEquals("Первое число меньше второго", NumberComparator.compare(2, 4));
    }

    @Test
    void testNumbersEqual() {
        assertEquals("Числа равны", NumberComparator.compare(7,7));
    }
}