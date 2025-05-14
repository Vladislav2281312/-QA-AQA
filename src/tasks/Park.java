package tasks;

public class Park {
    private String name;
    private String location;
    private Attraction[] attractions; // Массив аттракционов
    private int attractionCount; // Количество аттракционов

    // Конструктор класса Park
    public Park(String name, String location, int maxAttractions) {
        this.name = name;
        this.location = location;
        this.attractions = new Attraction[maxAttractions];
        this.attractionCount = 0;
    }

    // Метод для добавления аттракциона
    public void addAttraction(String name, String openingTime, String closingTime, double price) {
        if (attractionCount < attractions.length) {
            attractions[attractionCount++] = new Attraction(name, openingTime, closingTime, price);
        } else {
            System.out.println("Cannot add more attractions. Maximum limit reached.");
        }
    }

    // Метод для отображения информации о парке и его аттракционах
    public void displayInfo() {
        System.out.println("Park Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Attractions:");
        for (int i = 0; i < attractionCount; i++) {
            attractions[i].displayInfo();
        }
    }

    // Внутренний класс Attraction
    private class Attraction {
        private String name;
        private String openingTime;
        private String closingTime;
        private double price;


        public Attraction(String name, String openingTime, String closingTime, double price) {
            this.name = name;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.price = price;
        }

        // Метод для отображения информации об аттракционе
        public void displayInfo() {
            System.out.println("  - Name: " + name);
            System.out.println("    Opening Time: " + openingTime);
            System.out.println("    Closing Time: " + closingTime);
            System.out.println("    Price: $" + price);
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Park amusementPark = new Park("Wonderland", "New York", 3);

        amusementPark.addAttraction("Roller Coaster", "10:00 AM", "10:00 PM", 15.50);
        amusementPark.addAttraction("Ferris Wheel", "11:00 AM", "11:00 PM", 10.00);
        amusementPark.addAttraction("Haunted House", "12:00 PM", "9:00 PM", 12.00);

        amusementPark.displayInfo();
    }
}