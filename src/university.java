import java.util.*;

public class university {

    // Класс Student
    static class Student {
        private String name;
        private String group;
        private int course;
        private Map<String, Double> grades; // оценки по предметам

        public Student(String name, String group, int course, Map<String, Double> grades) {
            this.name = name;
            this.group = group;
            this.course = course;
            this.grades = new HashMap<>(grades);
        }

        public String getName() {
            return name;
        }

        public String getGroup() {
            return group;
        }

        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
        }

        public double getAverageGrade() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0.0;
            for (Double grade : grades.values()) {
                sum += grade;
            }
            return sum / grades.size();
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", group='" + group + '\'' +
                    ", course=" + course +
                    ", averageGrade=" + getAverageGrade() +
                    '}';
        }
    }

    // Метод для удаления студентов со средним баллом < 3
    public static void removeStudentsWithLowGrades(List<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    // Метод для повышения курса студента, если средний балл >= 3
    public static void promoteStudents(List<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    // Метод для печати имен студентов на заданном курсе
    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на курсе " + course + ":");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    // Пример использования
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();

        // Создаем студентов
        studentList.add(new Student("Иван Иванов", "Группа А", 1, Map.of("Математика", 4.0, "Физика", 3.5)));
        studentList.add(new Student("Петр Петров", "Группа Б", 2, Map.of("Математика", 2.0, "Физика", 2.5)));
        studentList.add(new Student("Светлана Смирнова", "Группа А", 1, Map.of("Математика", 3.0, "Физика", 3.0)));
        studentList.add(new Student("Алексей Кузнецов", "Группа В", 3, Map.of("Математика", 4.5, "Физика", 4.0)));

        // Удаляем студентов со средним баллом < 3
        removeStudentsWithLowGrades(studentList);

        // Повышаем курс студентам с средним баллом >= 3
        promoteStudents(studentList);

        // Создаем множество студентов для метода printStudents
        Set<Student> studentSet = new HashSet<>(studentList);

        // Печатаем студентов на курсе 2
        printStudents(studentSet, 2);
    }
}