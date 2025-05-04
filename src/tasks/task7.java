package tasks;

public class task7 {
    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void main(String[] args) {
        System.out.println(isNegative(-5));  // true
        System.out.println(isNegative(3));   // false
        System.out.println(isNegative(0));   // false
    }
}