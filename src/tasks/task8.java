package tasks;

public class task8 {
    public static void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        printStringMultipleTimes("Hello, world!", 3); // Выводит строку 3 раза
    }
}
