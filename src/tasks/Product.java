package tasks;

import java.time.LocalDate;

public class Product {
    private String name;
    private LocalDate productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean bookingStatus;


    public Product(String name, LocalDate productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean bookingStatus) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }


    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Production Date: " + productionDate);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Country of Origin: " + countryOfOrigin);
        System.out.println("Price: " + price);
        System.out.println("Booking Status: " + (bookingStatus ? "Booked" : "Available"));
    }


    public static void main(String[] args) {
        // Создаем новый объект Product для смартфона
        Product product = new Product("Smartphone", LocalDate.of(2023, 5, 20),
                "Apple", "USA", 999.99, true);
        product.displayInfo();
    }
}