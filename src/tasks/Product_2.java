package tasks;

import java.time.LocalDate;

public class Product_2 {
    private String name;
    private LocalDate productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean bookingStatus;


    public Product_2(String name, LocalDate productionDate, String manufacturer,
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
        System.out.println(); // Для разделения товаров
    }


    public static void main(String[] args) {
        // Объявляем массив объектов Product
        Product_2[] productsArray = new Product_2[5];

        // Заполняем массив объектами Product
        productsArray[0] = new Product_2("Samsung S25 Ultra", LocalDate.of(2025, 2, 1),
                "Samsung Corp.", "Korea", 5599.99, true);

        productsArray[1] = new Product_2("iPhone 14 Pro", LocalDate.of(2023, 9, 16),
                "Apple Inc.", "USA", 999.99, false);

        productsArray[2] = new Product_2("Google Pixel 7", LocalDate.of(2022, 10, 13),
                "Google LLC", "USA", 599.99, false);

        productsArray[3] = new Product_2("OnePlus 10 Pro", LocalDate.of(2022, 1, 11),
                "OnePlus", "China", 899.99, true);

        productsArray[4] = new Product_2("Xiaomi Mi 12", LocalDate.of(2021, 12, 28),
                "Xiaomi Inc.", "China", 749.99, false);

        // Вывод информации о каждом товаре в массиве
        for (Product_2 product_2 : productsArray) {
            product_2.displayInfo();
        }
    }
}
