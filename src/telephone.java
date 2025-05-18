import java.util.*;

public class telephone {
    // Используем Map, где ключ - фамилия, значение - список номеров телефонов
    private Map<String, List<String>> directory;

    public telephone() {
        directory= new HashMap<>();
    }

    // Метод для добавления записи
    public void add(String фамилия, String номерТелефона) {
        // Проверяем, есть ли уже такая фамилия
        if (!directory.containsKey(фамилия)) {
            directory.put(фамилия, new ArrayList<>());
        }
        // Добавляем номер в список для этой фамилии
        directory.get(фамилия).add(номерТелефона);
    }

    // Метод для поиска номеров по фамилии
    public List<String> get(String фамилия) {
        return directory.getOrDefault(фамилия, Collections.emptyList());
    }

    // Для демонстрации работы
    public static void main(String[] args) {
        telephone directory = new telephone();

        // Добавляем записи
        directory.add("Иванов", "123-45-67");
        directory.add("Петров", "234-56-78");
        directory.add("Иванов", "987-65-43");
        directory.add("Сидоров", "345-67-89");

        // Ищем номера по фамилии
        System.out.println("Телефоны Иванова:");
        for (String номер : directory.get("Иванов")) {
            System.out.println(номер);
        }

        System.out.println("Телефоны Петрова:");
        for (String номер : directory.get("Петров")) {
            System.out.println(номер);
        }

        System.out.println("Телефоны Сидорова:");
        for (String номер : directory.get("Сидоров")) {
            System.out.println(номер);
        }

        System.out.println("Телефоны с несуществующей фамилией:");
        List<String> номера = directory.get("Некто");
        if (номера.isEmpty()) {
            System.out.println("Нет записей");
        }
    }
}