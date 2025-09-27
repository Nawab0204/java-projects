import java.util.*;

public class CosmicCampus {
    // ========== SPACE CADET CLASS ==========
    private static class SpaceCadet {
        private String callsign;
        private String stardustID;
        private Map<SpaceCourse, Double> cosmicGrades = new HashMap<>();

        public SpaceCadet(String callsign) {
            this.callsign = callsign;
            this.stardustID = "COSMIC-" + UUID.randomUUID().toString().substring(0, 5) + "-⭐";
        }

        public void warpIntoCourse(SpaceCourse course) {
            System.out.println("\n🚀 Engaging warp drive for " + callsign + "...");
            simulateWarpAnimation();
            if (course.enrollCadet()) {
                cosmicGrades.put(course, null);
                System.out.println("🌌 Success! Welcome to planet " + course.planetCode);
            } else {
                System.out.println("💥 Course blackhole detected! Too many cadets!");
            }
        }

        private void simulateWarpAnimation() {
            try {
                String[] warpFrames = {"▁", "▂", "▃", "▄", "▅", "▆", "▇", "█"};
                for (String frame : warpFrames) {
                    System.out.print("\r" + frame + " Warping...");
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                System.out.println("\n⚠️ Warp drive malfunction!");
            }
        }
    }

    // ========== SPACE COURSE CLASS ==========
    private static class SpaceCourse {
        String planetCode;
        private String cosmicName;
        private int gravityCapacity;
        private int currentAliens;
        private static int totalColonized = 0;

        public SpaceCourse(String code, String name, int capacity) {
            this.planetCode = code;
            this.cosmicName = name;
            this.gravityCapacity = capacity;
            System.out.println(generateSpaceFact());
        }

        private String generateSpaceFact() {
            String[] facts = {
                    "🛸 New planet: " + planetCode + " | Fun fact: Rains coffee twice a year! ☕",
                    "🛸 New planet: " + planetCode + " | Fun fact: Debugging done with tentacles 🦑",
                    "🛸 New planet: " + planetCode + " | Fun fact: Sunrise compiles C++ in 0.3s 🕒"
            };
            return facts[new Random().nextInt(facts.length)];
        }

        public boolean enrollCadet() {
            if (currentAliens < gravityCapacity) {
                currentAliens++;
                totalColonized++;
                return true;
            }
            return false;
        }
    }

    // ========== MAIN MISSION CONTROL ==========
    private static Map<String, SpaceCadet> cadets = new HashMap<>();
    private static List<SpaceCourse> galaxy = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printAsciiTitle();

        while (true) {
            System.out.println("\n===== SPACE COMMAND MENU =====");
            System.out.println("1. 🪐 Colonize New Planet");
            System.out.println("2. 👾 Recruit Space Cadet");
            System.out.println("3. 📡 Beam Stardust Grade");
            System.out.println("4. 🚀 Launch Quantum Debugger (Exit)");
            System.out.print("Enter command: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": addPlanet(scanner); break;
                case "2": recruitCadet(scanner); break;
                case "3": beamGrade(scanner); break;
                case "4":
                    System.out.println("\n🌠 May the code force be with you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("💣 KABOOM! Invalid command. Try again!");
            }
        }
    }

    private static void addPlanet(Scanner scanner) {
        System.out.print("Enter planet code: ");
        String code = scanner.nextLine().trim();
        if(code.equalsIgnoreCase("makeitso")) {
            System.out.println("🖖 Engage! Picard Protocol: Infinite capacity unlocked!");
            code = "USS-ENTERPRISE";
        }
        System.out.print("Enter course name: ");
        String name = scanner.nextLine().trim();
        galaxy.add(new SpaceCourse(code, name, 3)); // Small capacity for drama
    }

    private static void recruitCadet(Scanner scanner) {
        System.out.print("Enter cadet callsign: ");
        String name = scanner.nextLine().trim();
        SpaceCadet cadet = new SpaceCadet(name);
        cadets.put(cadet.stardustID, cadet);

        System.out.print("Enter planet code: ");
        String code = scanner.nextLine().trim();
        Optional<SpaceCourse> course = galaxy.stream()
                .filter(c -> c.planetCode.equalsIgnoreCase(code))
                .findFirst();

        if (course.isPresent()) {
            cadet.warpIntoCourse(course.get());
        } else {
            System.out.println("💥 Planet not found!");
        }
    }

    private static void beamGrade(Scanner scanner) {
        System.out.print("Enter cadet ID: ");
        String id = scanner.nextLine().trim();
        SpaceCadet cadet = cadets.get(id);

        if (cadet == null) {
            System.out.println("☄️ Cadet lost in space!");
            return;
        }

        try {
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            String reaction = grade >= 90 ? "🌟 SUPERSTAR!" :
                    grade >= 70 ? "🚀 Warp-speed success!" :
                            "☄️ Meteor shower of retakes!";
            System.out.println(reaction + " Grade recorded for " + cadet.callsign);
        } catch (InputMismatchException e) {
            System.out.println("💣 Invalid grade! Must be a number!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void printAsciiTitle() {
        System.out.println(""" 
            Nawab
            """);
    }
}