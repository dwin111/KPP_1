import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Створення списку студентів
        List<Student> students = Arrays.asList(
                new Student("Олег", "Сидоров", "Гуртожиток 2", 201, 1700.0, 20, 2),
                new Student("Анна", "Коваленко", "Гуртожиток 1", 101, 1500.0, 21, 0),
                new Student("Олена", "Лисенко", "Гуртожиток 2", 201, 1700.0, 20, 1),
                new Student("Сергій", "Мельник", "Гуртожиток 3", 301, 1400.0, 19, 0)
        );

        // 1. Розділити студентів на пільговиків і не пільговиків
        List<Student> beneficiaries = new ArrayList<Student>();
        List<Student> nonBeneficiaries = new ArrayList<Student>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.isBeneficiary()) {
                beneficiaries.add(student);
            } else {
                nonBeneficiaries.add(student);
            }
        }
        System.out.println("Пільговики:");
        for (int i = 0; i < beneficiaries.size(); i++) {
            System.out.println(beneficiaries.get(i));
        }
        System.out.println("\nНе пільговики:");
        for (int i = 0; i < nonBeneficiaries.size(); i++) {
            System.out.println(nonBeneficiaries.get(i));
        }
        // 2. Згрупувати студентів за гуртожитками
        Map<String, List<Student>> studentsByDormitory = new HashMap<String, List<Student>>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            String dormitory = student.getDormitory();
            List<Student> dormitoryStudents = studentsByDormitory.get(dormitory);
            if (dormitoryStudents == null) {
                dormitoryStudents = new ArrayList<Student>();
                studentsByDormitory.put(dormitory, dormitoryStudents);
            }
            dormitoryStudents.add(student);
        }
        System.out.println("\nСтуденти за гуртожитками:");
        for (Map.Entry<String, List<Student>> entry : studentsByDormitory.entrySet()) {
            String dormitory = entry.getKey();
            List<Student> dormStudents = entry.getValue();
            System.out.println("Гуртожиток: " + dormitory);
            for (int i = 0; i < dormStudents.size(); i++) {
                System.out.println(dormStudents.get(i));
            }
        }
        // 3. Створити колекцію з кількістю студентів у кожній кімнаті
        Map<String, Integer> roomOccupancy = new HashMap<String, Integer>();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            String roomKey = student.getDormitory() + " Кімната " + student.getRoomNumber();
            Integer count = roomOccupancy.get(roomKey);
            if (count == null) {
                count = 0;
            }
            roomOccupancy.put(roomKey, count + 1);
        }
        System.out.println("\nКількість студентів у кімнатах:");
        for (Map.Entry<String, Integer> entry : roomOccupancy.entrySet()) {
            String room = entry.getKey();
            int count = entry.getValue();
            System.out.println(room + ", Кількість студентів: " + count);
        }
        // 4. Відсортувати студентів за віком та кількістю пільг
        List<Student> sortedStudents = new ArrayList<Student>(students);
        // Сортування списку
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int ageCompare = Integer.compare(s1.getAge(), s2.getAge());
                if (ageCompare != 0) {
                    return ageCompare;
                } else {
                    return Integer.compare(s1.getNumberOfBenefits(), s2.getNumberOfBenefits());
                }
            }
        });
        System.out.println("\nСтуденти, відсортовані за віком та кількістю пільг:");
        for (int i = 0; i < sortedStudents.size(); i++) {
            System.out.println(sortedStudents.get(i));
        }
        // 5. Вивести список всіх унікальних номерів кімнат
        Set<Integer> uniqueRoomNumbers = new HashSet<Integer>();
        for (int i = 0; i < students.size(); i++) {
            uniqueRoomNumbers.add(students.get(i).getRoomNumber());
        }
        System.out.println("\nУнікальні номери кімнат:");
        for (Integer roomNumber : uniqueRoomNumbers) {
            System.out.println(roomNumber);
        }
        // 6. Знайти студента з найбільшою платою за проживання
        Optional<Student> maxFeeStudent = Optional.empty();
        if (!students.isEmpty()) {
            Student maxStudent = students.get(0);
            for (int i = 1; i < students.size(); i++) {
                if (students.get(i).getAccommodationFee() > maxStudent.getAccommodationFee()) {
                    maxStudent = students.get(i);
                }
            }
            maxFeeStudent = Optional.of(maxStudent);
        }
        System.out.println("\nСтудент з найбільшою платою за проживання:");
        if (maxFeeStudent.isPresent()) {
            System.out.println(maxFeeStudent.get());
        } else {
            System.out.println("Студентів немає");
        }
    }
}