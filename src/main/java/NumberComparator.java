public class NumberComparator {

    public static String compare(int a, int b) {
        if (a > b) return "Первое число больше второго";
        else if (a < b) return "Первое число меньше второго";
        else return "Числа равны";
    }
}