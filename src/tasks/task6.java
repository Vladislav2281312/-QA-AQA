package tasks;

public class task6 {
    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Число " + number + " является положительным.");
        } else {
            System.out.println("Число " + number + " является отрицательным.");
        }
    }

    public static void main(String[] args) {
        checkNumber(5);   // Число 5 является положительным.
        checkNumber(-3);  // Число -3 является отрицательным.
        checkNumber(0);   // Число 0 является положительным.
    }
}