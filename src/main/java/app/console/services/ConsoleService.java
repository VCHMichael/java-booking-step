package app.console.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleService {
    private static Scanner scanner = new Scanner(System.in);

    public static String readCommand(String section) {
        String input;
        List<String> allowedCommands = new ArrayList<>(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")
        );

        if (section.equals("bookingMenu")) {
            allowedCommands = new ArrayList<>(
                    Arrays.asList("1", "2"));
        }

        while (true) {
            System.out.println("\nВведите комманду: ");
            input = scanner.nextLine().trim().toLowerCase().split(" ")[0];
            if (allowedCommands.contains(input)) return input;
            System.out.println("Вы ввели недопустимую комманду. Пожалуйста, попробуйте снова.");
            System.out.println("Список разрешенных комманд выведен на экран.");
        }
    }

    public static int readFlightId(String prompt) {
        String input;

        Pattern pattern = Pattern.compile("^[0-9]+$");

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();

            boolean enteredIdIsCorrect = pattern.matcher(input).find();
            if (enteredIdIsCorrect) break;
            else {
                System.out.println("Вы ввели неправильный номер рейса. Образец: 1.");
                System.out.println("Пожалуйста, введите правильный номер рейса согласно образцу.");
            }
        }
        return Integer.parseInt(input);
    }

}
