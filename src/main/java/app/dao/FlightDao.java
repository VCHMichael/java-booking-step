package app.dao;

import app.exception.NoEntityException;
import app.model.FlightModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface FlightDao {
    public FlightModel getFlightById(int id) throws IOException, NoEntityException;
    public ArrayList<FlightModel> getAllFlightsPerDay();
    public ArrayList<FlightModel> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException;
}
