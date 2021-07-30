package app.flight.services;

import app.flight.NoEntityException;
import app.flight.dao.CollectionFlightsDao;
import app.flight.dao.FlightDao;
import app.flight.model.Flight;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class FlightServices {
    FlightDao flightDao;

    public FlightServices(FlightDao flightDao) {
        this.flightDao = flightDao;
    }
    public FlightServices() throws IOException {
        this.flightDao = new CollectionFlightsDao();
    }

    public Flight getFlightById(int id) throws IOException, NoEntityException {
        return flightDao.getFlightById(id);
    }

    public ArrayList<Flight> getAllFlightsPerDay() {
        return flightDao.getAllFlightsPerDay();
    }

    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException {
        return flightDao.getSearchedFlightsForReservation(destination, date, ticketsCount);
    }
}
