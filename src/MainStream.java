import java.util.*;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        // Створення списку студентів
        List<Student> students = Arrays.asList(
                new Student("Олег", "Сидоров", "Гуртожиток 2", 201, 1700.0, 20, 2),
                new Student("Анна", "Коваленко", "Гуртожиток 1", 101, 1500.0, 21, 0),
                new Student("Олена", "Лисенко", "Гуртожиток 2", 201, 1700.0, 20, 1),
                new Student("Сергій", "Мельник", "Гуртожиток 3", 301, 1400.0, 19, 0)
        );

        // 1. Розділити студентів на пільговиків і не пільговиків
        List<Student> beneficiaries = students.stream()
                .filter(Student::isBeneficiary)
                .collect(Collectors.toList());

        List<Student> nonBeneficiaries = students.stream()
                .filter(student -> !student.isBeneficiary())
                .collect(Collectors.toList());

        System.out.println("Пільговики:");
        beneficiaries.forEach(System.out::println);

        System.out.println("\nНе пільговики:");
        nonBeneficiaries.forEach(System.out::println);

        // 2. Згрупувати студентів за гуртожитками
        Map<String, List<Student>> studentsByDormitory = students.stream()
                .collect(Collectors.groupingBy(Student::getDormitory));

        System.out.println("\nСтуденти за гуртожитками:");
        studentsByDormitory.forEach((dormitory, dormStudents) -> {
            System.out.println("Гуртожиток: " + dormitory);
            dormStudents.forEach(System.out::println);
        });

        // 3. Створити колекцію з кількістю студентів у кожній кімнаті
        Map<String, Long> roomOccupancy = students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.getDormitory() + " Кімната " + student.getRoomNumber(),
                        Collectors.counting()
                ));

        System.out.println("\nКількість студентів у кімнатах:");
        roomOccupancy.forEach((room, count) -> {
            System.out.println(room + ", Кількість студентів: " + count);
        });

        // 4. Відсортувати студентів за віком та кількістю пільг
        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingInt(Student::getAge)
                        .thenComparingInt(Student::getNumberOfBenefits))
                .collect(Collectors.toList());

        System.out.println("\nСтуденти, відсортовані за віком та кількістю пільг:");
        sortedStudents.forEach(System.out::println);

        // 5. Вивести список всіх унікальних номерів кімнат
        Set<Integer> uniqueRoomNumbers = students.stream()
                .map(Student::getRoomNumber)
                .collect(Collectors.toSet());

        System.out.println("\nУнікальні номери кімнат:");
        uniqueRoomNumbers.forEach(System.out::println);

        // 6. Знайти студента з найбільшою платою за проживання
        Optional<Student> maxFeeStudent = students.stream()
                .max(Comparator.comparingDouble(Student::getAccommodationFee));

        System.out.println("\nСтудент з найбільшою платою за проживання:");
        maxFeeStudent.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Студентів немає")
        );
    }
}