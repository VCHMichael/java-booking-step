package app.console;

import app.flight.controller.FlightController;
import app.flight.model.Flight;
import app.reservation.controller.ReservationController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import static app.console.services.ConsoleService.readCommand;
import static app.console.services.ConsoleService.readFlightId;

public class Console {

    private static FlightController flightController;
    private static ReservationController reservationController;
//    private static UsersController usersController;
    static String input;

    private static HashMap<String, Callable<Void>> mainMenuCommands;

    static String mainMenu =
            "\n\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "СЕРВИС БРОНИРОВАНИЯ АВИАБИЛЕТОВ:\n" +
                    "1.\tОтобразить онлайн-табло.\n" +
                    "2.\tПосмотреть информацию о рейсе\n" +
                    "3.\tПоиск и бронирование рейсов\n" +
                    "4.\tОтменить бронирование\n" +
                    "5.\tМои рейсы\n" +
                    "6.\tВыход\n";
    public Console() throws IOException {
        flightController = new FlightController();
        reservationController = new ReservationController();
    }

    public static void addComandsMainMenu (){
        mainMenuCommands = new HashMap<>();
        mainMenuCommands.put("1", () -> {
            System.out.println("<<< Вы выбрали команду №1 - ОТОБРАЗИТЬ ОНЛАЙН-ТАБЛО >>>");
            ArrayList<Flight> getAllFlightsPerDay = flightController.getAllFlightsPerDay();
            System.out.println(getAllFlightsPerDay);
            return null;
        });
        mainMenuCommands.put("2", () -> {
            System.out.println("<<< Вы выбрали команду №2 - ПОСМОТРЕТЬ ИНФОРМАЦИЮ О РЕЙСЕ >>>");
            int idOfFlight = readFlightId("Введите номер интересующего Вас рейса (его ID):");
            try {
                Flight flightByIdID = flightController.getFlightById(idOfFlight);
                System.out.println(flightByIdID);
            } catch (Exception e){
                System.out.println(e);
            }
            return null;
        });
        mainMenuCommands.put("3", () -> {
            System.out.println("<<< Вы выбрали команду №3 - ПОИСК И БРОНИРОВАНИЕ РЕЙСОВ >>>");
//            reservationController.reserve();
            return null;
        });


    }
    private static void initialization() {
        addComandsMainMenu();
    }

    public static void executeCommandByName(String section, String commandName) throws Exception {
        if (section.equals("main")) {
            Callable<Void> commandToBeExecuted = mainMenuCommands.get(commandName);
            commandToBeExecuted.call();
        }
//        else if (section.equals("loginMenu")) {
//            Callable<Void> commandToBeExecuted = loginMenuCommands.get(commandName);
//            commandToBeExecuted.call();
//        } else if (section.equals("bookingMenu")) {
//            Callable<Void> commandToBeExecuted = bookingMenuCommands.get(commandName);
//            commandToBeExecuted.call();
//        }
    }



    public static void main(String[] args) throws Exception {
        initialization();

        while(mainMenu.length() > 0){
            System.out.println(mainMenu);
            input = readCommand("main");
            executeCommandByName("main", input);
        }

        main(null);

    }
}

