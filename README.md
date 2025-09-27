# java-projects
Mindbender Quiz Game  Java Project Submission
1. Introduction
This document contains the source code and explanation for the 'MindBender Quiz Game' developed as part of the Programming Assignment Unit 1 for CS 1102-01. The program is a simple Java console-based quiz that asks five multiple-choice questions, validates user input, evaluates the answers, calculates the score, and displays a final result.
2. Assignment Requirements
The assignment requires implementing a quiz program in Java that:
- Asks 5 multiple-choice questions
- Validates input (A, B, C, or D)
- Uses if and switch statements
- Calculates score as percentage
- Displays results
- Includes proper formatting, structure, and code readability
3. Java Code
Below is the complete Java code for the quiz game:


import java.util.Scanner;

public class MindBenderQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("🌟 Welcome to the MindBender Quiz Challenge! 🌟");
        System.out.println("Answer the following 5 questions by typing A, B, C, or D.\n");

        char[] correctAnswers = {'B', 'C', 'A', 'D', 'C'};
        char[] userAnswers = new char[5];

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

        for (int i = 0; i < 5; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Your answer (A/B/C/D): ");
            char answer = scanner.next().toUpperCase().charAt(0);

            while (answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D') {
                System.out.print("Invalid input. Please enter A, B, C, or D: ");
                answer = scanner.next().toUpperCase().charAt(0);
            }

            userAnswers[i] = answer;

            switch (answer) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    if (answer == correctAnswers[i]) {
                        score++;
                    }
                    break;
            }

            System.out.println();
        }

        double percentage = (score / 5.0) * 100;

        System.out.println("🧠 Quiz Completed!");
        System.out.println("You answered " + score + " out of 5 questions correctly.");
        System.out.printf("Final Score: %.2f%%\n", percentage);

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

4. Code Explanation
- The program starts by welcoming the user and explaining the quiz format.
- It uses arrays to store questions, options, and correct answers.
- A for-loop iterates over each question and prints it with options.
- The program validates user input using a while-loop.
- A switch statement checks if the user's answer matches the correct one.
- The final score is calculated as a percentage.
- The user is shown feedback based on their score.
5. Testing and Output
The program was tested with various inputs to ensure:
- Proper validation of input
- Accurate calculation of score
- Smooth program flow
- Friendly output messaging

Screenshots of the program running in the IDE and the console output are included separately in the submission.
6. Conclusion
This Java quiz game fulfills all the requirements of the assignment by demonstrating proper use of control structures, input validation, code organization, and user interaction. It is a simple yet effective example of applying fundamental programming concepts.
7. Sample Output (Console View)

🌟 Welcome to the MindBender Quiz Challenge! 🌟
Answer the following 5 questions by typing A, B, C, or D.

1. What is the output of: System.out.println(3 + "7");
A. 10
B. 37
C. Error
D. 3 + 7
Your answer (A/B/C/D): B

2. Which Java keyword is used to inherit a class?
A. implement
B. implements
C. extends
D. inherit
Your answer (A/B/C/D): C

3. What is the default value of a boolean in Java?
A. false
B. true
C. null
D. 0
Your answer (A/B/C/D): A

4. Which data type would you use for a single 16-bit Unicode character?
A. byte
B. int
C. string
D. char
Your answer (A/B/C/D): D

5. What is the result of: 10 > 9 && 5 < 2?
A. true
B. false
C. false
D. Compilation error
Your answer (A/B/C/D): C

🧠 Quiz Completed!
You answered 5 out of 5 questions correctly.
Final Score: 100.00%
🎉 Perfect score! You're a Java genius!

