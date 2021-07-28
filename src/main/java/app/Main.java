package app;

import app.flight.NoEntityException;
import app.flight.dao.CollectionFlightsDao;


import java.io.IOException;
import java.text.ParseException;


public class Main {
  public static void main(String[] args) throws NoEntityException, IOException, ParseException {

    // How to use Flights Examples
    CollectionFlightsDao collectionFlightsDao = new CollectionFlightsDao();
    System.out.println(collectionFlightsDao.getFlightById(1));
    System.out.println(collectionFlightsDao.getAllFlightsPerDay());
    System.out.println(collectionFlightsDao.getSearchedFlightsForReservation("Picassinos", "2021-07-29", 2));
  }

}
