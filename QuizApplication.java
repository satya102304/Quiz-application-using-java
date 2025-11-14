import java.io.*;
import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEACHER MODE ===");
        System.out.print("Enter question file name (e.g., questions.txt): ");
        String filename = sc.nextLine();

        List<Question> questions = loadQuestions(filename);

        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions found. Exiting.");
            return;
        }

        System.out.println("\n=== STUDENT MODE ===");
        System.out.println("Press ENTER when you're ready to start the quiz...");
        sc.nextLine();

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q.questionText);
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            System.out.print("Your answer: ");
            int ans = sc.nextInt();

            if (ans == q.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer = " + q.correctOption);
            }
        }

        System.out.println("\n=== QUIZ COMPLETED ===");
        System.out.println("You scored " + score + " out of " + questions.size());
    }

    // Load questions from file
    private static List<Question> loadQuestions(String filename) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String qText = line;

                String[] opts = new String[4];
                for (int i = 0; i < 4; i++) {
                    opts[i] = br.readLine();
                }

                int correct = Integer.parseInt(br.readLine());

                questions.add(new Question(qText, opts, correct));
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        return questions;
    }
}
