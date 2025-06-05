import org.testng.Assert;
import org.testng.annotations.Test;

public class ArithmeticTest {
    private Arithmetic ops = new Arithmetic();

    @Test
    public void testAdd() {
        Assert.assertEquals(ops.add(10, 5), 15);
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(ops.subtract(10, 5), 5);
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(ops.multiply(10, 5), 50);
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(ops.divide(10,2), 5.0);

        // Проверка деления на ноль
        try {
            ops.divide(10,0);
            assert false; // должно выбросить исключение
        } catch (ArithmeticException e) {
            Assert.assertTrue(true);
        }
    }
}