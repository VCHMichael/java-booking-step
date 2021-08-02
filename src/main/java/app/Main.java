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
    public static void main(String[] args) throws NoEntityException, IOException, ParseException {
        try {
            Console console = new Console();
            console.main(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println(cause);
        }
    }
}
