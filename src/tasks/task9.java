package tasks;

public class task9 {
    public static boolean isLeapYear(int year) {
        // Проверяем условия для високосного года
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0; // Если делится на 400, то високосный
            }
            return true; // Если делится на 4, но не на 100, то високосный
        }
        return false; // Если не делится на 4, то не високосный
    }

    public static void main(String[] args) {
        System.out.println(isLeapYear(2020)); // true
        System.out.println(isLeapYear(1900)); // false
        System.out.println(isLeapYear(2000)); // true
        System.out.println(isLeapYear(2021)); // false
    }
}
