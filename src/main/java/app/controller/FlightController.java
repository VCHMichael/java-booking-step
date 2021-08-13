package app.controller;

import app.exception.NoEntityException;
import app.model.FlightModel;
import app.service.FlightServices;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class FlightController {
    FlightServices flightServices;


    public FlightController(FlightServices flightServices) {
        this.flightServices = flightServices;
    }

    public FlightController() throws IOException {
        flightServices = new FlightServices();
    }


    public FlightModel getFlightById(int id) throws IOException, NoEntityException {
        return flightServices.getFlightById(id);
    }

    public ArrayList<FlightModel> getAllFlightsPerDay() {
        return flightServices.getAllFlightsPerDay();
    }

    public ArrayList<FlightModel> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException {
        return flightServices.getSearchedFlightsForReservation(destination, date, ticketsCount);
    }
}
