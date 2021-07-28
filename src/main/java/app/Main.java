package app;

import app.logger.Logger;
import app.reservation.controller.ReservationController;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    ReservationController reservationController = new ReservationController();
    Logger logger = new Logger(Main.class);


    logger.error("zhopa!", new RuntimeException());
    logger.info("Hello");

//   String uuid = UUID.randomUUID().toString();
//    System.out.println(uuid);
  }
}
