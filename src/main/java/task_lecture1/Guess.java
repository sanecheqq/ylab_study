package task_lecture1;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) throws Exception {
        int number = new Random().nextInt(99) + 1;
        int maxAttempts = 10;
        System.out.println("Я загадал число от 1 до 99. У тебя " + maxAttempts + " попыток угадать.");
        userGuessTheNumber(number, maxAttempts);
    }

    public static void userGuessTheNumber(int number, int maxAttempts) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            for (int attempt = maxAttempts - 1; attempt >= 0; --attempt) {
                int guess = scanner.nextInt();
                if (guess < number) {
                    System.out.println("Мое число больше! Осталось " + attempt + " попыток.");
                } else if (guess > number) {
                    System.out.println("Мое число меньше! Осталось " + attempt + " попыток.");
                } else {
                    System.out.println("Ты угадал с " + (maxAttempts - attempt) + " попытки!");
                    return;
                }
            }
            System.out.println("Ты не угадал!");
        }
    }
}
