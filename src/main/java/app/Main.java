package app;

import app.console.Console;
import app.flight.NoEntityException;
import app.flight.dao.CollectionFlightsDao;
import app.reservation.controller.ReservationController;
import app.user.services.UserService;

import java.io.IOException;
import java.text.ParseException;
import app.flight.controller.FlightController;
import app.reservation.controller.ReservationController;
import app.flight.services.FlightServices;
public class Main {
    public static FlightController fc;
    public static ReservationController rc;
    public static FlightServices flightServices;


    public static void main(String[] args) throws NoEntityException, IOException, ParseException {
//        fc = new FlightController();
//        rc = new ReservationController();

        try {
            Console console = new Console();
            console.main(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println(cause);
        }


        // How to use Reservation Examples
        ReservationController reservationController = new ReservationController();

        UserService userService = new UserService();

        // How to use Flights Examples
        CollectionFlightsDao collectionFlightsDao = new CollectionFlightsDao();
        System.out.println(collectionFlightsDao.getFlightById(1));
        System.out.println(collectionFlightsDao.getAllFlightsPerDay());
        System.out.println(collectionFlightsDao.getSearchedFlightsForReservation("Picassinos", "2021-07-29", 2));
    }
}
