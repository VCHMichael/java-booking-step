package app.flight.dao;

import app.flight.model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;

public class CollectionFlightsDao implements FlightDao{
    @Override
    public Flight getFlightById(int id) {
        return null;
    }

    @Override
    public ArrayList<Flight> getAllFlightsPerDay() {
        return null;
    }

    @Override
    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, LocalDate date, int ticketsCount) {
        return null;
    }
}
