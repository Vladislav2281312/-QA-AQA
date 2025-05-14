package tasks;
// Базовый класс
public class Animal {
    protected static int countAnimals = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
        countAnimals++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }

    public static int getCountAnimals() {
        return countAnimals;
    }
}

// Класс Собака
 class Dog extends Animal {
    private static int countDogs = 0;
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;

    public Dog(String name) {
        super(name);
        countDogs++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать так далеко.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= MAX_SWIM_DISTANCE) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть так далеко.");
        }
    }

    public static int getCountDogs() {
        return countDogs;
    }
}

// Класс Кот
 class Cat extends Animal {
    private static int countCats = 0;
    private static final int MAX_RUN_DISTANCE = 200;
    private static final int CAN_SWIM = 0; // коты не умеют плавать

    private boolean satiety; // сытость
    private boolean hungry; // голоден

    public Cat(String name, boolean hungry) {
        super(name);
        this.hungry = hungry;
        this.satiety = false; // изначально голоден, сытость false
        countCats++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать так далеко.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Коты не умеют плавать");
    }

    // Метод для покушать из миски
    public void eat(Misska misska, int foodAmount) {
        if (foodAmount <= 0) {
            System.out.println("Некорректное количество еды");
            return;
        }
        if (misska.getFood() >= foodAmount) {
            boolean success = misska.decreaseFood(foodAmount);
            if (success) {
                this.satiety = true;
                System.out.println(name + " покушал(а) и стал(а) сытым(ой).");
            } else {
                System.out.println("Не удалось покушать, недостаточно еды в миске.");
            }
        } else {
            System.out.println("В миске недостаточно еды для этого количества");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public boolean isHungry() {
        return hungry;
    }

    public static int getCountCats() {
        return countCats;
    }
}

// Класс Миска с едой
 class Misska {
    private int food;

    public Misska(int initialFood) {
        this.food = initialFood >= 0 ? initialFood : 0;
    }

    // Метод уменьшения количества еды в миске
    public boolean decreaseFood(int amount) {
        if (amount > food || amount <= 0) return false;
        food -= amount;
        return true;
    }

    // Метод добавления еды в миску
    public void addFood(int amount) {
        if (amount > 0)
            food += amount;
    }

    // Получение текущего количества еды
    public int getFood() {
        return food;
    }
}

// Основной класс для тестирования
 class Main {

    public static void main(String[] args) {

        // Создаем животных и котов
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        Cat cat1 = new Cat("Мурзик", true);
        Cat cat2 = new Cat("Барсик", true);

        // Проверка ограничений на бег и плавание у собак
        dog1.run(400); // допустимо
        dog1.run(600); // нельзя

        dog2.swim(8); // допустимо
        dog2.swim(15); // нельзя

        // Проверка ограничений у котов
        cat1.run(150); // допустимо
        cat2.run(250); // нельзя

        cat1.swim(5); // коты не умеют плавать

        // Создаем миску с едой и добавляем еду
        Misska misska = new Misska(20);
        misska.addFood(10); // теперь в миске 30 еды

        // Массив котов для кормления
        Cat[] cats = {cat1, cat2};

        int foodPerCat = 15;

        for (Cat c : cats) {
            c.eat(misska, foodPerCat);
            System.out.println(c.name + " сытость: " + c.isSatiety());
        }

        System.out.println("Остаток еды в миске: " + misska.getFood());

        // Вывод общего количества животных, собак и котов
        System.out.println("Общее количество животных: " + Animal.getCountAnimals());
        System.out.println("Общее количество собак: " + Dog.getCountDogs());
        System.out.println("Общее количество котов: " + Cat.getCountCats());
    }
}