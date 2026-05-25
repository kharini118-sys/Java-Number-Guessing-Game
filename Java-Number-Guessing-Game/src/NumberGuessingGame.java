import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundsWon = 0;

        System.out.println("===== NUMBER GUESSING GAME =====");

        boolean playAgain = true;

        while (playAgain) {

            int number = random.nextInt(100) + 1;
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nGuess the number between 1 and 100");
            System.out.println("You have only " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                attempts++;

                if (guess == number) {
                    System.out.println("Correct! You guessed the number.");
                    guessedCorrectly = true;

                    int score = (maxAttempts - attempts + 1) * 10;
                    totalScore += score;
                    roundsWon++;

                    System.out.println("Score for this round: " + score);
                    break;
                }

                else if (guess > number) {
                    System.out.println("Too high!");
                }

                else {
                    System.out.println("Too low!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("\nGame Over!");
                System.out.println("The correct number was: " + number);
            }

            System.out.println("\n===== SCORE BOARD =====");
            System.out.println("Rounds Won: " + roundsWon);
            System.out.println("Total Score: " + totalScore);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next();

            playAgain = choice.equalsIgnoreCase("yes");
        }

        System.out.println("\nThank you for playing!");
        sc.close();
    }
}
