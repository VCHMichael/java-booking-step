package app.service;

import app.exception.NoEntityException;
import app.dao.CollectionFlightsDao;
import app.dao.FlightDao;
import app.model.FlightModel;

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

    public FlightModel getFlightById(int id) throws IOException, NoEntityException {
        return flightDao.getFlightById(id);
    }

    public ArrayList<FlightModel> getAllFlightsPerDay() {
        return flightDao.getAllFlightsPerDay();
    }

    public ArrayList<FlightModel> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException {
        return flightDao.getSearchedFlightsForReservation(destination, date, ticketsCount);
    }
}
