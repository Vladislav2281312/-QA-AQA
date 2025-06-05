import org.testng.Assert;
import org.testng.annotations.Test;

public class GeometryTest {
    private Geometry geometry = new Geometry();

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(geometry.triangleArea(10, 5), 25.0);
        Assert.assertEquals(geometry.triangleArea(7, 3), 10.5);
        Assert.assertEquals(geometry.triangleArea(0, 5), 0.0);
    }
}