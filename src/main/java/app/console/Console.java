package app.console;

import app.flight.controller.FlightController;
import app.flight.model.Flight;
import app.reservation.controller.ReservationController;
import app.user.model.UserModel;
import app.user.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import static app.console.services.ConsoleService.*;

public class Console {
    private static  ArrayList<Flight> flightsConsideredAtTheMoment;

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
            String destination = destination("Введите место назначения");
            String date = date("Введите желаемую дату вылета. В формате yyyy-mm-dd");
            int seats = seats("Введите количество пассажиров");
            ArrayList<Flight> availableFlights =  flightController.getSearchedFlightsForReservation(destination,date,seats);
            System.out.println(availableFlights);
            flightsConsideredAtTheMoment = availableFlights;

            String bookingMenuCommandsStr =
                    "\n\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Дальнейшие действия:\n" +
                            "1.\tВыбрать маршрут для бронирования.\n" +
                            "2.\tВернуться в предыдущее меню.\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
            System.out.println(bookingMenuCommandsStr);

            input = readCommand("bookingMenu");
            executeCommandByName("bookingMenu", input);


            return null;
        });


    }

    public static void addCommandsBookingMenu(){
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
            passengerList.add(userService.create(name,lastName));
        }
        System.out.println(flightRoute);
        System.out.println(passengerList);
        reservationController.reserve(passengerList,flightRoute.getId());




//        String activeUserLogin = usersController.getActiveUser().getLogin();
//        Booking newBooking = new Booking(activeUserLogin, flightRoute, passengerList);
//        bookingsController.createBooking(newBooking);
//        flightsController.applySeatsReserve4Booking(newBooking);
//
//        System.out.println("Вы успешно забронировали билеты. Ниже информация о Вашей брони:");
//        System.out.println("****************************************************************");
//        System.out.println(newBooking.prettyFormat());
//        System.out.println("****************************************************************");
    }
    private static void initialization() {
        addComandsMainMenu();
        addCommandsBookingMenu();
    }

    public static void executeCommandByName(String section, String commandName) throws Exception {
        if (section.equals("main")) {
            Callable<Void> commandToBeExecuted = mainMenuCommands.get(commandName);
            commandToBeExecuted.call();
        }
        else if (section.equals("bookingMenu")) {
           Callable<Void> commandToBeExecuted = bookingMenuCommands.get(commandName);
          commandToBeExecuted.call();
      }
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

