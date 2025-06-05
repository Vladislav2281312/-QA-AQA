import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberTest {
    private Number comparator = new Number();

    @Test
    public void testCompare() {
        Assert.assertEquals(comparator.compare(5,3), "Больше");
        Assert.assertEquals(comparator.compare(2,4), "Меньше");
        Assert.assertEquals(comparator.compare(7,7), "Равно");
    }
}