// Исключение для неправильного размера массива
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

// Исключение для некорректных данных в ячейке массива
class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

public class ArrayProcessor {

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // Проверка размера массива
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен иметь 4 строки");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + " должна иметь 4 элемента");
            }
        }

        int sum = 0;
        // Проход по всем элементам массива
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String value = array[i][j];
                try {
                    int number = Integer.parseInt(value);
                    sum += number;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "Некорректные данные в ячейке [" + i + "][" + j + "]: '" + value + "'"
                    );
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // Пример корректного массива
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Массив с неправильным размером (например, одна строка длиной 3)
        String[][] invalidSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        // Массив с некорректными данными (например, текст вместо числа)
        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "seven", "12"},
                {"13", "14", "15", "16"}
        };

        // Массив для генерации ArrayIndexOutOfBoundsException
        String[][] outOfBoundsArray = new String[4][4];

        // Попытка обращения к несуществующему элементу для вызова ArrayIndexOutOfBoundsException
        try {
            outOfBoundsArray[0][0] = "1"; // корректно
            outOfBoundsArray[0][1] = "2"; // корректно
            outOfBoundsArray[0][2] = "3"; // корректно
            outOfBoundsArray[0][3] = "4"; // корректно

            outOfBoundsArray[1][0] = "5"; // корректно
            outOfBoundsArray[1][1] = "6"; // корректно
            outOfBoundsArray[1][2] = "7"; // корректно
            outOfBoundsArray[1][3] = "8"; // корректно

            outOfBoundsArray[2][0] = "9"; // корректно
            outOfBoundsArray[2][1] = "10"; // корректно
            outOfBoundsArray[2][2] = "11"; // корректно

            // Здесь намеренно выйдем за границы массива, чтобы вызвать ArrayIndexOutOfBoundsException
            outOfBoundsArray[2][4] = "12"; // Ошибка!

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Обнаружена ошибка ArrayIndexOutOfBounds: попытка обращения к несуществующему индексу.");
        }

        System.out.println("\nОбработка правильного массива:");
        try {
            int result = processArray(validArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        System.out.println("\nОбработка массива с неправильным размером:");
        try {
            int result = processArray(invalidSizeArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        System.out.println("\nОбработка массива с некорректными данными:");
        try {
            int result = processArray(invalidDataArray);
            System.out.println("Сумма элементов: "+ result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: "+ e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: "+ e.getMessage());
        }
    }
}