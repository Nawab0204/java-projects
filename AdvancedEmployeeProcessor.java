import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enhanced Employee Data Processor demonstrating:
 * - Function interface implementation
 * - Advanced stream operations
 * - Parallel processing capabilities
 * - Statistical analysis
 * - Custom data aggregation
 */
class Employee {
    private final String name;
    private final int age;
    private final String department;
    private final double salary;
    private final String employeeId;

    public Employee(String name, int age, String department, double salary, String employeeId) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
        this.employeeId = employeeId;
    }

    // Accessors
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getEmployeeId() { return employeeId; }
}

public class AdvancedEmployeeProcessor {
    private static final int AGE_THRESHOLD = 30;
    private static final int DATA_SIZE = 10000;

    public static void main(String[] args) {
        // Generate large synthetic dataset
        List<Employee> employees = generateEmployeeData(DATA_SIZE);

        // 1. Function Interface: Name-Department Concatenator
        Function<Employee, String> nameDeptMapper = emp ->
                String.format("%s (%s) - %s", emp.getEmployeeId(), emp.getName(), emp.getDepartment());

        // 2. Stream Processing Pipeline
        long startTime = System.currentTimeMillis();

        // Create concurrent-safe collection for results
        Collection<String> employeeIdentifiers = Collections.newSetFromMap(new ConcurrentHashMap<>());

        // Primary processing pipeline
        double averageSalary = employees.parallelStream()
                .peek(emp -> employeeIdentifiers.add(nameDeptMapper.apply(emp)))  // Side-effect only in concurrent collection
                .filter(emp -> emp.getAge() > AGE_THRESHOLD)
                .collect(Collectors.teeing(
                        Collectors.averagingDouble(Employee::getSalary),
                        Collectors.groupingByConcurrent(
                                Employee::getDepartment,
                                Collectors.summarizingDouble(Employee::getSalary)
                        ),
                        (avg, statsMap) -> {
                            // Secondary stream for statistical analysis
                            statsMap.forEach((dept, stats) ->
                                    System.out.printf("%-15s | Count: %-4d | Avg: $%,8.2f | Max: $%,8.2f%n",
                                            dept, stats.getCount(), stats.getAverage(), stats.getMax())
                            );
                            return avg;
                        }
                ));

        // 3. Performance Metrics
        long duration = System.currentTimeMillis() - startTime;
        System.out.printf("%nProcessed %,d records in %d ms%n", DATA_SIZE, duration);
        System.out.printf("Average salary for employees > %d: $%,.2f%n", AGE_THRESHOLD, averageSalary);

        // 4. Additional Features
        // a. Salary distribution analysis
        Map<String, Long> salaryDistribution = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        emp -> {
                            double salary = emp.getSalary();
                            if (salary < 50_000) return "<50K";
                            if (salary < 100_000) return "50K-100K";
                            return ">100K";
                        },
                        Collectors.counting()
                ));

        // b. Departmental seniority index
        Map<String, Double> seniorityIndex = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        Employee::getDepartment,
                        Collectors.averagingInt(emp -> emp.getAge() - 22)  // Years since typical graduation
                ));

        // Output additional analytics
        System.out.println("\n=== Salary Distribution ===");
        salaryDistribution.forEach((range, count) ->
                System.out.printf("%-8s: %5d employees%n", range, count));

        System.out.println("\n=== Departmental Seniority ===");
        seniorityIndex.forEach((dept, avgExperience) ->
                System.out.printf("%-15s: %.1f years average experience%n", dept, avgExperience));
    }

    /**
     * Generates synthetic employee data for processing
     * @param size Number of records to generate
     * @return List of Employee objects
     */
    private static List<Employee> generateEmployeeData(int size) {
        String[] departments = {"Engineering", "Marketing", "Finance", "HR", "Operations", "R&D"};
        Random random = new Random();

        return Stream.generate(() -> {
            String name = "EMP" + String.format("%05d", random.nextInt(size));
            int age = 22 + random.nextInt(40);
            String dept = departments[random.nextInt(departments.length)];
            double salary = 40_000 + (random.nextDouble() * 120_000);
            String id = "ID" + UUID.randomUUID().toString().substring(0, 8);
            return new Employee(name, age, dept, salary, id);
        }).limit(size).collect(Collectors.toList());
    }
}