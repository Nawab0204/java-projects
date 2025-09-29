import java.util.Scanner;

public class MindBenderQuiz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("🌟 Welcome to the MindBender Quiz Challenge! 🌟");
        System.out.println("Answer the following 5 questions by typing A, B, C, or D.\n");

        // Array of correct answers
        char[] correctAnswers = {'B', 'C', 'A', 'D', 'C'};

        // Track user answers
        char[] userAnswers = new char[5];

        // Questions and options
        String[] questions = {
                "1. What is the output of: System.out.println(3 + \"7\");",
                "2. Which Java keyword is used to inherit a class?",
                "3. What is the default value of a boolean in Java?",
                "4. Which data type would you use for a single 16-bit Unicode character?",
                "5. What is the result of: 10 > 9 && 5 < 2?"
        };

        String[][] options = {
                {"A. 10", "B. 37", "C. Error", "D. 3 + 7"},
                {"A. implement", "B. implements", "C. extends", "D. inherit"},
                {"A. false", "B. true", "C. null", "D. 0"},
                {"A. byte", "B. int", "C. string", "D. char"},
                {"A. true", "B. false", "C. false", "D. Compilation error"}
        };

        // Loop through questions
        for (int i = 0; i < 5; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Your answer (A/B/C/D): ");
            char answer = scanner.next().toUpperCase().charAt(0);

            // Input validation
            while (answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D') {
                System.out.print("Invalid input. Please enter A, B, C, or D: ");
                answer = scanner.next().toUpperCase().charAt(0);
            }

            userAnswers[i] = answer;

            // Use switch to check answer
            switch (answer) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    if (answer == correctAnswers[i]) {
                        score++;
                    }
                    break;
                default:
                    // Should never happen due to input validation
                    break;
            }

            System.out.println();
        }

        // Calculate score
        double percentage = (score / 5.0) * 100;

        // Output result
        System.out.println("🧠 Quiz Completed!");
        System.out.println("You answered " + score + " out of 5 questions correctly.");
        System.out.printf("Final Score: %.2f%%\n", percentage);

        // Optional: fun feedback
        if (percentage == 100) {
            System.out.println("🎉 Perfect score! You're a Java genius!");
        } else if (percentage >= 80) {
            System.out.println("💪 Great job! You're on your way!");
        } else if (percentage >= 50) {
            System.out.println("👍 Good effort. Keep practicing!");
        } else {
            System.out.println("📚 Don't worry. Study up and try again!");
        }

        scanner.close();
    }
}
