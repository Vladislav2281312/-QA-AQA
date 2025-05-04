package tasks;

public class task14 {
    public static void main(String[] args) {

        int len = 5;
        int initialValue = 10;

        int[] array = createArray(len, initialValue);

        // Выводим массив
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    // Метод для создания массива
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len]; // Создаем массив заданной длины

        // Заполняем массив значением initialValue
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }

        return array; // Возвращаем заполненный массив
    }
}