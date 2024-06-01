import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private int targetNumber;
    private int attempts;
    private int maxAttempts = 5;
    private int score;

    public NumberGuessingGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1; // Random number between 1 and 100
        attempts = 0;
        score = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean isGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it correctly.");

        while (attempts < maxAttempts && !isGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            try {
                int playerGuess = Integer.parseInt(scanner.nextLine());
                attempts++;

                if (playerGuess == targetNumber) {
                    System.out.println("Well done! You've guessed the right number!");
                    isGuessedCorrectly = true;
                    score++;
                } else if (playerGuess < targetNumber) {
                    System.out.println("Too low! Aim higher.");
                } else {
                    System.out.println("Too high! Aim lower.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        if (!isGuessedCorrectly) {
            System.out.println("Oops! You've used all your attempts. The number was " + targetNumber);
        }

        System.out.println("Game Over! Your score: " + score);
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.play();
    }
}
