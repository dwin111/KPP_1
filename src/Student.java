public class Student {
    // Поля студента
    private String name;
    private String surname;
    private String dormitory;
    private int roomNumber;
    private double accommodationFee;
    private int age;
    private int numberOfBenefits;

    // Конструктор
    public Student(String name, String surname, String dormitory, int roomNumber, double accommodationFee, int age, int numberOfBenefits) {
        this.name = name;
        this.surname = surname;
        this.dormitory = dormitory;
        this.roomNumber = roomNumber;
        this.accommodationFee = accommodationFee;
        this.age = age;
        this.numberOfBenefits = numberOfBenefits;
    }

    // Геттери
    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getDormitory() { return dormitory; }

    public int getRoomNumber() { return roomNumber; }

    public double getAccommodationFee() { return accommodationFee; }

    public int getAge() { return age; }

    public int getNumberOfBenefits() { return numberOfBenefits; }

    public boolean isBeneficiary() {
        return numberOfBenefits > 0;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", Гуртожиток: " + dormitory + ", Кімната: " + roomNumber +
                ", Плата: " + accommodationFee + ", Вік: " + age + ", Кількість пільг: " + numberOfBenefits;
    }
}

