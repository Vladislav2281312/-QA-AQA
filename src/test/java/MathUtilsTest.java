import org.testng.Assert;
import org.testng.annotations.Test;

public class MathUtilsTest {
    private MathUtils mathUtils = new MathUtils();

    @Test
    public void testFactorial() {
        Assert.assertEquals(mathUtils.factorial(0), 1);
        Assert.assertEquals(mathUtils.factorial(1), 1);
        Assert.assertEquals(mathUtils.factorial(5), 120);
        Assert.assertEquals(mathUtils.factorial(6), 720);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        mathUtils.factorial(-3);
    }
}