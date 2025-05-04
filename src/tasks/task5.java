package tasks;

public class task5 {
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void main(String[] args) {
        System.out.println(isSumInRange(7, 5));  // true (12)
        System.out.println(isSumInRange(10, 10)); // true (20)
        System.out.println(isSumInRange(3, 4));   // false (7)
    }
}