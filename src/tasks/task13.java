package tasks;

public class task13 {
    public static void main(String[] args) {
        // Размерность массива
        int n = 5;
        int[][] array = new int[n][n];


        for (int i = 0; i < n; i++) {
            array[i][i] = 1;          // Главная диагональ
            array[i][n - 1 - i] = 1;  // Побочная диагональ
        }

        // Выводим массив
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); // Переход на новую строку после каждой строки массива
        }
    }
}
