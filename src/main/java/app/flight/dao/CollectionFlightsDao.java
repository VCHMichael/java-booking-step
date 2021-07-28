package app.flight.dao;

import app.flight.NoEntityException;
import app.flight.model.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class CollectionFlightsDao implements FlightDao {
    private final List<Flight> flightBd;

    public CollectionFlightsDao() throws IOException {
        File database = new File("src/main/resources/flightDatabase.json");
        ObjectMapper mapper = new ObjectMapper();
        flightBd = mapper.readValue(database, new TypeReference<List<Flight>>() {
        });
    }

    @Override
    public Flight getFlightById(int id) throws NoEntityException {
        return flightBd.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoEntityException(id));
    }

    @Override
    public ArrayList<Flight> getAllFlightsPerDay() {
        Date startDate = java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
        Date endDate = java.sql.Date.valueOf(LocalDateTime.now().plusHours(24).toLocalDate());
        return flightBd.stream()
                .filter(e -> e.getDate().after(startDate) && e.getDate().before(endDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Flight> getSearchedFlightsForReservation(String destination, String date, int ticketsCount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date userDate = sdf.parse(date);

        return flightBd.stream()
                .filter(e -> e.getDestination().toLowerCase().equals(destination.toLowerCase()))
                .filter(e -> e.getSeats() > ticketsCount)
                .filter(e -> {
                    long diff = e.getDate().getTime() - userDate.getTime();
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    return days == 0;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
