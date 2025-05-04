package tasks;

public class task10{
    public static void main(String[] args) {

        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        // Вывод исходного массива
        System.out.print("Исходный массив: ");
        printArray(array);

        // Замена элементов
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1; // Заменяем 0 на 1
            } else {
                array[i] = 0; // Заменяем 1 на 0
            }
        }

        // Вывод измененного массива
        System.out.print("Измененный массив: ");
        printArray(array);
    }

    // Метод для вывода массива в консоль
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
