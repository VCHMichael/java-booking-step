package app;

import app.logger.Logger;
import app.reservation.controller.ReservationController;
import app.flight.NoEntityException;
import app.flight.dao.CollectionFlightsDao;

import app.logger.Logger;
import app.reservation.controller.ReservationController;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws NoEntityException, IOException, ParseException {

        // How to use Reservation Examples
        ReservationController reservationController = new ReservationController();
  public static void main(String[] args) throws IOException {
    ReservationController reservationController = new ReservationController();
    Logger logger = new Logger(Main.class);

        // How to use Flights Examples
        CollectionFlightsDao collectionFlightsDao = new CollectionFlightsDao();
        System.out.println(collectionFlightsDao.getFlightById(1));
        System.out.println(collectionFlightsDao.getAllFlightsPerDay());
        System.out.println(collectionFlightsDao.getSearchedFlightsForReservation("Picassinos", "2021-07-29", 2));

        // Logger
        Logger logger = new Logger(Main.class);
        logger.error("zhopa!", new RuntimeException());
        logger.info("Hello");

    }
    logger.error("zhopa!", new RuntimeException());
    logger.info("Hello");

//   String uuid = UUID.randomUUID().toString();
//    System.out.println(uuid);
  }
}
