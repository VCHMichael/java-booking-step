package app.console;

import app.flight.controller.FlightController;
import app.flight.model.Flight;
import app.logger.Logger;
import app.reservation.controller.ReservationController;
import app.reservation.model.ReservationModel;
import app.user.model.UserModel;
import app.user.services.UserService;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

import static app.console.services.ConsoleService.*;

public class Console {
    private static final Logger logger = new Logger(Console.class);
    private static ArrayList<Flight> flightsConsideredAtTheMoment;

    private static FlightController flightController;
    private static ReservationController reservationController;
    private static UserService userService;
    static String input;
    static List<UserModel> passengerList;

    private static HashMap<String, Callable<Void>> mainMenuCommands;
    private static HashMap<String, Callable<Void>> bookingMenuCommands;

    static String mainMenu =
            "\n\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "СЕРВИС БРОНИРОВАНИЯ АВИАБИЛЕТОВ:\n" +
                    "1.\tОтобразить онлайн-табло.\n" +
                    "2.\tПосмотреть информацию о рейсе\n" +
                    "3.\tПоиск и бронирование рейсов\n" +
                    "4.\tОтменить бронирование\n" +
                    "5.\tМои рейсы\n" +
                    "6.\tВыход\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";



    public static void addComandsMainMenu() {
        mainMenuCommands = new HashMap<>();
        mainMenuCommands.put("1", () -> {
            System.out.println("<<< Вы выбрали команду №1 - ОТОБРАЗИТЬ ОНЛАЙН-ТАБЛО >>>");
            ArrayList<Flight> getAllFlightsPerDay = flightController.getAllFlightsPerDay();
            for (Flight f : getAllFlightsPerDay) {
                String id = "ID рейса: " + f.getId() + " | ";
                String date = "Дата вылета: " + f.getDate() + " | ";
                String destination = "Прибытие: " + f.getDestination() + " | ";
                String seats = "Свободные места: " + f.getSeats() + " | ";
                String result = id + date + destination + seats;
                System.out.println(result);
            }

            return null;
        });
        mainMenuCommands.put("2", () -> {
            System.out.println("<<< Вы выбрали команду №2 - ПОСМОТРЕТЬ ИНФОРМАЦИЮ О РЕЙСЕ >>>");
            int idOfFlight = readFlightId("Введите номер интересующего Вас рейса (его ID):");
            try {
                Flight flightByIdID = flightController.getFlightById(idOfFlight);
                String id = "ID рейса: " + flightByIdID.getId() + " | ";
                String date = "Дата вылета: " + flightByIdID.getDate() + " | ";
                String destination = "Прибытие: " + flightByIdID.getDestination() + " | ";
                String seats = "Свободные места: " + flightByIdID.getSeats() + " | ";
                String result = id + date + destination + seats;
                System.out.println(result);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        });
        mainMenuCommands.put("3", () -> {
            System.out.println("<<< Вы выбрали команду №3 - ПОИСК И БРОНИРОВАНИЕ РЕЙСОВ >>>");
            String destination = destination("Введите место назначения");
            String date = date("Введите желаемую дату вылета. В формате yyyy-mm-dd");
            int seats = seats("Введите количество пассажиров");
            ArrayList<Flight> availableFlights = flightController.getSearchedFlightsForReservation(destination, date, seats);
            for (Flight f : availableFlights) {
                String id = "ID рейса: " + f.getId() + " | ";
                String datestr = "Дата вылета: " + f.getDate() + " | ";
                String destinationstr = "Прибытие: " + f.getDestination() + " | ";
                String seatsstr = "Свободные места: " + f.getSeats() + " | ";
                String result = id + datestr + destinationstr + seatsstr;
                System.out.println("Доступные рейсы: \n" + result);
            }
            flightsConsideredAtTheMoment = availableFlights;

            String bookingMenuCommandsStr =
                    "\n\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Дальнейшие действия:\n" +
                            "1.\tЖелаете забронировать билеты?.\n" +
                            "2.\tВернуться в предыдущее меню.\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
            System.out.println(bookingMenuCommandsStr);

            input = readCommand("bookingMenu");
            executeCommandByName("bookingMenu", input);


            return null;
        });
        mainMenuCommands.put("4", () -> {
            System.out.println("<<< Вы выбрали команду №4 - ОТМЕНИТЬ БРОНИРОВАНИЕ >>>");
            String idOfBooking = readReservationId("Введите ID Вашего бронирования");
            reservationController.cancel(idOfBooking);
            System.out.println("Вы успешно отменили бронирование");
            return null;
        });
        mainMenuCommands.put("5", () -> {
            System.out.println("<<< Вы выбрали команду №5 - МОИ РЕЙСЫ >>>");
            String name = readString("Для поиска рейса введите имя латиницей");
            String lastName = readString("Введите фамилию латиницей");
            Map<String, Long> userReserves = reservationController.getUserReserves(name, lastName);
            if (userReserves != null && userReserves.size() < 1) {
                System.out.println("<<< У вас нет забронированных рейсов >>>");
            } else {
                System.out.println("<<<Ваши забронированные рейсы >>>");
                userReserves.forEach((reservationId, flightId) -> {
                    try {
                        Flight flightByIdID = flightController.getFlightById(Math.toIntExact(flightId));
                        String id = "ID рейса: " + flightByIdID.getId() + " | ";
                        String date = "Дата вылета: " + flightByIdID.getDate() + " | ";
                        String destination = "Прибытие: " + flightByIdID.getDestination() + " | ";
                        String seats = "Свободные места: " + flightByIdID.getSeats() + " | ";
                        String result = id + date + destination + seats;
                        System.out.println(result);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                });
            }
            return null;
        });
        mainMenuCommands.put("6", () -> {
            System.out.println("<<< Вы выбрали команду №6 - ЗАВЕРШИТЬ РАБОТУ ПРИЛОЖЕНИЯ >>>");
            System.exit(0);
            return null;
        });
    }

    public Console() throws IOException {
        flightController = new FlightController();
        reservationController = new ReservationController();
        userService = new UserService();
        passengerList = new ArrayList<>();
    }

    public static void addCommandsBookingMenu() {
        bookingMenuCommands = new HashMap<>();
        bookingMenuCommands.put("1", () -> {
            System.out.println("<<< Вы выбрали команду №1 - Забронировать билеты на маршрут ");
            createBooking();
            return null;
        });

        bookingMenuCommands.put("2", () -> {
            System.out.println("<<< Вы выбрали команду №2 - Вернуться в главное меню >>>");
            return null;
        });
    }

    public static void createBooking() throws IOException {
        int maxNumbOfFlightInList = flightsConsideredAtTheMoment.size();
        int chosenItemInList = readNumber("Введите порядковый номер маршрута в данном списке:",
                1, maxNumbOfFlightInList);
        int indexOfItemInList = chosenItemInList - 1;
        // из-за того, что нумерация отфильтрованных маршрутов на экране начинается с единицы, а
        // индексы в структуре данных ArrayList начинаются с ноля. Поэтому мы и делаем эту
        // корректировку на -1.
        Flight flightRoute = flightsConsideredAtTheMoment.get(indexOfItemInList);

        int numbOfPassengers = readNumber("Введите количество пассажиров, для которых Вы " +
                "хотите приобрести билеты:", 1, 10);
        for (int i = 0; i < numbOfPassengers; i++) {
            String name = readString("Введите имя латиницей");
            String lastName = readString("Введите фамилию латиницей");
//            userService.create(name,lastName);
            passengerList.add(userService.create(name, lastName));
        }
        ReservationModel reserve = reservationController.reserve(passengerList, flightRoute.getId());
        String s = "ID бронирования:" + reserve.getId() + " | ";
        String id = "ID рейса: " + reserve.getFlightId() + " | ";
        String passangers = "Пассажиры: " + reserve.getPassengers() + " | ";
        System.out.println("****************************************************************");
        System.out.println("Вы успешно забронировали билеты. Информация по бронированию :");
        System.out.println(s + id + passangers);
        System.out.println("****************************************************************");
    }

    public static void executeCommandByName(String section, String commandName) throws Exception {
        if (section.equals("main")) {
            Callable<Void> commandToBeExecuted = mainMenuCommands.get(commandName);
            commandToBeExecuted.call();
        } else if (section.equals("bookingMenu")) {
            Callable<Void> commandToBeExecuted = bookingMenuCommands.get(commandName);
            commandToBeExecuted.call();
        }
    }

    private static void initialization() {
        addComandsMainMenu();
        addCommandsBookingMenu();
    }

    public static void main(String[] args) throws Exception {
        initialization();

        while (mainMenu.length() > 0) {
            System.out.println(mainMenu);
            input = readCommand("main");
            executeCommandByName("main", input);
        }

        main(null);

    }
}

