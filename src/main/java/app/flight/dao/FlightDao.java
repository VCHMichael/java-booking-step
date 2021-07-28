package app.flight.dao;

import app.flight.NoEntityException;
import app.flight.model.Flight;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface FlightDao {
    public Flight getFlightById(int id) throws IOException, NoEntityException;
    public ArrayList<Flight> getAllFlightsPerDay();
    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException;
}
