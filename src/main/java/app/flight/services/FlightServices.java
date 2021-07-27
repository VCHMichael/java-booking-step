package app.flight.services;

import app.flight.dao.FlightDao;
import app.flight.model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightServices {
    FlightDao flightDao;

    public FlightServices(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public Flight getFlightById(int id) {
        return flightDao.getFlightById(id);
    }

    public ArrayList<Flight> getAllFlightsPerDay() {
        return flightDao.getAllFlightsPerDay();
    }

    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, LocalDate date, int ticketsCount) {
        return flightDao.getSearchedFlightsForReservation(destination, date, ticketsCount);
    }
}
