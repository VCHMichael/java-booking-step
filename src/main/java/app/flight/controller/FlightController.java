package app.flight.controller;

import app.flight.NoEntityException;
import app.flight.model.Flight;
import app.flight.services.FlightServices;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class FlightController {
    FlightServices flightServices;

    public FlightController(FlightServices flightServices) {
        this.flightServices = flightServices;
    }

    public Flight getFlightById(int id) throws IOException, NoEntityException {
        return flightServices.getFlightById(id);
    }

    public ArrayList<Flight> getAllFlightsPerDay() {
        return flightServices.getAllFlightsPerDay();
    }

    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException {
        return flightServices.getSearchedFlightsForReservation(destination, date, ticketsCount);
    }
}
