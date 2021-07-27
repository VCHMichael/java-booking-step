package app.flight.dao;

import app.flight.model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;

public interface FlightDao {
    public Flight getFlightById(int id);
    public ArrayList<Flight> getAllFlightsPerDay();
    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, LocalDate date, int ticketsCount);
}
