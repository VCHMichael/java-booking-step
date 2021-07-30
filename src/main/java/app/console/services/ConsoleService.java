package app.console.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
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
    public static String destination(String prompt) {
        String input;

        Pattern pattern = Pattern.compile("^[a-zA-z]+$");

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();

            boolean enteredIdIsCorrect = pattern.matcher(input).find();
            if (enteredIdIsCorrect) break;
            else {
                System.out.println("Вы ввели не верное место назначения. Образец: Kyiv");
                System.out.println("Пожалуйста, введите правильное место назначения согласно образцу.");
            }
        }
        return input;
    }
    public static String date(String prompt) {
        String input;

        Pattern pattern = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();

            boolean enteredIdIsCorrect = pattern.matcher(input).find();
            if (enteredIdIsCorrect) break;
            else {
                System.out.println("Вы ввели не верную дату. Образец: 2021-07-30");
                System.out.println("Пожалуйста, введите правильную дату согласно образцу.");
            }
        }
        return input;
    }
    public static int seats(String prompt) {
        String input;

        Pattern pattern = Pattern.compile("^[1-9]+$");

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();

            boolean enteredIdIsCorrect = pattern.matcher(input).find();
            if (enteredIdIsCorrect) break;
            else {
                System.out.println("Вы ввели некорректное значение. Образец: 1.");
                System.out.println("Пожалуйста, введите значение согласно образцу.");
            }
        }
        return Integer.parseInt(input);
    }
    public static int readNumber(String prompt, int min, int max) {
        String input;
        int value = Integer.MIN_VALUE;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильный формат данных.");
                value = Integer.MIN_VALUE;
            }
            if (value >= min && value <= max)
                break;
            System.out.println("Пожалуйста введите целое число в диапазоне от " + min + " до " + max);
        }
        return value;
    }

    public static String readString(String prompt) {
        String input;

        Pattern pattern = Pattern.compile("\\d", Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();

            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                System.out.println("Вы ввели неправильный формат данных. В данной строке не " +
                        "должно быть цифр.");
                continue;
            }

            if (input.length() > 17 || input.length() < 2) {
                System.out.println("Размер введенной Вами строки должен быть в диапазоне от 2 до " +
                        "17 символов.");
            }

            if (!matcher.find() && input.length() < 17 && input.length() > 2)
                break;
        }
        input = input.toLowerCase();
        String formattedInput = input.substring(0, 1).toUpperCase() + input.substring(1);
        return formattedInput;
    }

}
