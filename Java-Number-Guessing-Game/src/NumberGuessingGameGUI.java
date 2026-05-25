import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private int number;
    private int attempts;
    private int maxAttempts = 5;
    private int score = 0;
    private int roundsWon = 0;

    private JLabel titleLabel, messageLabel, attemptsLabel, scoreLabel;
    private JTextField guessField;
    private JButton guessButton, playAgainButton;

    Random random = new Random();

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        titleLabel = new JLabel("🎯 Number Guessing Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        messageLabel = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        attemptsLabel = new JLabel("Attempts Left: 5", JLabel.CENTER);
        scoreLabel = new JLabel("Score: 0 | Rounds Won: 0", JLabel.CENTER);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);

        add(titleLabel);
        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(scoreLabel);
        add(playAgainButton);

        startNewRound();

        guessButton.addActionListener(e -> checkGuess());
        playAgainButton.addActionListener(e -> startNewRound());

        setVisible(true);
    }

    private void startNewRound() {
        number = random.nextInt(100) + 1;
        attempts = 0;

        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts Left: " + maxAttempts);
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == number) {
                messageLabel.setText("🎉 Correct! The number was " + number);

                int roundScore = (maxAttempts - attempts + 1) * 10;
                score += roundScore;
                roundsWon++;

                scoreLabel.setText("Score: " + score + " | Rounds Won: " + roundsWon);

                endRound();
            } 
            else if (guess > number) {
                messageLabel.setText("📈 Too High! Try smaller.");
            } 
            else {
                messageLabel.setText("📉 Too Low! Try bigger.");
            }

            attemptsLabel.setText("Attempts Left: " + (maxAttempts - attempts));

            if (attempts == maxAttempts && guess != number) {
                messageLabel.setText("❌ Game Over! Correct number was " + number);
                endRound();
            }

            guessField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private void endRound() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}