import java.util.Scanner;

public class StudentRecordHub {
    // Student database configuration
    static final int MAX_CAPACITY = 100;
    static String[] studentNames = new String[MAX_CAPACITY];
    static String[] studentIDs = new String[MAX_CAPACITY];
    static int[] studentAges = new int[MAX_CAPACITY];
    static String[] studentGrades = new String[MAX_CAPACITY];
    static int studentCount = 0;

    // Main system interface
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🎓 Welcome to Student Record Hub v2.0 🎓");

        while (true) {
            displayMainMenu();
            int choice = getMenuChoice(scanner);

            switch (choice) {
                case 1 -> enrollNewStudent(scanner);
                case 2 -> modifyStudentRecord(scanner);
                case 3 -> displayStudentProfile(scanner);
                case 4 -> exitSystem();
                default -> System.out.println("⚠️ Invalid selection. Try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n========= Management Console =========");
        System.out.println("1. Enroll New Student");
        System.out.println("2. Modify Existing Record");
        System.out.println("3. View Student Profile");
        System.out.println("4. Exit System");
        System.out.print("Enter your choice (1-4): ");
    }

    // Student enrollment process
    private static void enrollNewStudent(Scanner scanner) {
        if (studentCount >= MAX_CAPACITY) {
            System.out.println("\n⚠️ System at full capacity. Cannot enroll new students.");
            return;
        }

        System.out.println("\n📋 New Student Enrollment");
        String name = validateName(scanner);
        String id = generateUniqueID(scanner);
        int age = validateAge(scanner);
        String grade = validateGrade(scanner);

        storeStudentData(name, id, age, grade);
        System.out.println("\n✅ Success! Student enrolled with ID: " + id);
    }

    // Student record modification
    private static void modifyStudentRecord(Scanner scanner) {
        System.out.print("\n✏️ Enter Student ID to modify: ");
        String targetID = scanner.nextLine().toUpperCase();
        int index = findStudentIndex(targetID);

        if (index == -1) {
            System.out.println("❌ No student found with ID: " + targetID);
            return;
        }

        System.out.println("\nEditing record for: " + studentNames[index]);
        updateStudentDetails(scanner, index);
        System.out.println("\n🔄 Record updated successfully!");
    }

    // Student profile display
    private static void displayStudentProfile(Scanner scanner) {
        System.out.print("\n🔍 Enter Student ID to view: ");
        String searchID = scanner.nextLine().toUpperCase();
        int index = findStudentIndex(searchID);

        if (index == -1) {
            System.out.println("❌ Student not found in records");
            return;
        }

        System.out.println("\n📄 Student Profile");
        System.out.println("Name  : " + studentNames[index]);
        System.out.println("ID    : " + studentIDs[index]);
        System.out.println("Age   : " + studentAges[index]);
        System.out.println("Grade : " + studentGrades[index]);
    }

    // Helper methods
    private static int getMenuChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String validateName(Scanner scanner) {
        while (true) {
            System.out.print("Full Name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("❌ Name cannot be empty");
        }
    }

    private static String generateUniqueID(Scanner scanner) {
        while (true) {
            System.out.print("Student ID: ");
            String id = scanner.nextLine().toUpperCase();
            if (findStudentIndex(id) == -1) return id;
            System.out.println("❌ ID already exists. Use a unique identifier");
        }
    }

    private static int validateAge(Scanner scanner) {
        while (true) {
            System.out.print("Age (12-25): ");
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age >= 12 && age <= 25) return age;
                System.out.println("❌ Age must be between 12-25");
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid age format");
            }
        }
    }

    private static String validateGrade(Scanner scanner) {
        final String[] VALID_GRADES = {"A", "B+", "B", "C+", "C", "D", "F"};
        while (true) {
            System.out.print("Grade (A, B+, B, C+, C, D, F): ");
            String grade = scanner.nextLine().toUpperCase();
            for (String valid : VALID_GRADES) {
                if (valid.equalsIgnoreCase(grade)) return grade;
            }
            System.out.println("❌ Invalid grade. Use standard letter grades");
        }
    }

    private static void storeStudentData(String name, String id, int age, String grade) {
        studentNames[studentCount] = name;
        studentIDs[studentCount] = id.toUpperCase();
        studentAges[studentCount] = age;
        studentGrades[studentCount] = grade.toUpperCase();
        studentCount++;
    }

    private static int findStudentIndex(String id) {
        String targetID = id.toUpperCase();
        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(targetID)) return i;
        }
        return -1;
    }

    private static void updateStudentDetails(Scanner scanner, int index) {
        System.out.print("New Name [current: " + studentNames[index] + "]: ");
        String newName = scanner.nextLine();
        if (!newName.isBlank()) studentNames[index] = newName;

        System.out.print("New Age [current: " + studentAges[index] + "]: ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isBlank()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                if (newAge >= 12 && newAge <= 25) {
                    studentAges[index] = newAge;
                } else {
                    System.out.println("⚠️ Age not updated. Must be 12-25");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid age format. Age not updated");
            }
        }

        System.out.print("New Grade [current: " + studentGrades[index] + "]: ");
        String newGrade = scanner.nextLine().toUpperCase();
        if (!newGrade.isBlank()) {
            if (isValidGrade(newGrade)) {
                studentGrades[index] = newGrade;
            } else {
                System.out.println("⚠️ Invalid grade. Grade not updated");
            }
        }
    }

    private static boolean isValidGrade(String grade) {
        return grade.matches("A|B\\+?|C\\+?|D|F");
    }

    private static void exitSystem() {
        System.out.println("\n👋 Thank you for using Student Record Hub!");
        System.exit(0);
    }
}