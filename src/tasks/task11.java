package tasks;

public class task11 {
    public static void main(String[] args) {

        int[] array = new int[100];


        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Заполняем элемент массива значением i + 1
        }

        // Выводим заполненный массив в консоль
        System.out.print("Заполненный массив: ");
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