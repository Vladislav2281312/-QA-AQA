import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void testValidTriangle() {
        double area = TriangleArea.area(3, 4, 5);
        assertEquals(6.0, area, 1e-6);
    }

    @Test
    void testInvalidTriangleSides() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.area(1, 2, 3);
        });
    }

    @Test
    void testNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.area(-3, 4, 5);
        });
    }
}