package tasks;

// Интерфейс для геометрических фигур
 interface Shape {
    // Методы для получения цветов
    String getFillColor();
    String getBorderColor();

    // Методы для установки цветов
    void setFillColor(String color);
    void setBorderColor(String color);

    // Методы для расчетов
    double getPerimeter();
    double getArea();

    // Дефолтные методы (можно переопределять)
    default double calculatePerimeter() {
        return getPerimeter();
    }

    default double calculateArea() {
        return getArea();
    }
}

// Класс для круга
class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}

// Класс для прямоугольника
class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}

// Класс для треугольника (например, по трем сторонам)
class Triangle implements Shape {
    private double sideA, sideB, sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double getArea() {
        // Используем формулу Герона
        double s = getPerimeter() / 2.0; // полупериметр
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }
}

// Основной класс с main()
public class Figures {
    public static void main(String[] args) {

        // Создаем фигуры с цветами заливки и границами
        Shape circle = new Circle(5.0, "Red", "Black");
        Shape rectangle = new Rectangle(4.0, 6.0, "Blue", "Green");
        Shape triangle = new Triangle(3.0, 4.0, 5.0, "Yellow", "Brown");

        // Вывод характеристик каждой фигуры в консоль
        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(triangle);
    }

    public static void printShapeInfo(Shape shape) {
        System.out.println("Фигура: " + shape.getClass().getSimpleName());
        System.out.println("Периметр: " + shape.calculatePerimeter());
        System.out.println("Площадь: " + shape.calculateArea());
        System.out.println("Цвет заливки: " + shape.getFillColor());
        System.out.println("Цвет границы: " + shape.getBorderColor());
        System.out.println("------------------------------");
    }
}