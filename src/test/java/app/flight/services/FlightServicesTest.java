package app.flight.services;

import app.flight.NoEntityException;
import app.flight.dao.FlightDao;
import app.flight.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.*;


class FlightServicesTest {
    private FlightServices flightServicesTest;
    Flight flightModel;
    @BeforeEach
    public void init() throws ParseException, IOException, NoEntityException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = sdf.parse("2021-07-29");
        FlightDao flightDaoTest = mock(FlightDao.class);
        flightServicesTest = new FlightServices(flightDaoTest);
        flightModel = new Flight();
        flightModel.setId(1);
        flightModel.setDestination("Paris");
        flightModel.setSeats(10);
        flightModel.setDate(testDate);

        when(flightDaoTest.getFlightById(1))
                .thenReturn(flightModel);
        when(flightDaoTest.getAllFlightsPerDay())
                .thenReturn(new ArrayList<>(Collections.singleton(flightModel)));
        when(flightDaoTest.getSearchedFlightsForReservation("Paris", "2021-07-29", 5))
                .thenReturn(new ArrayList<>(Collections.singleton(flightModel)));
    }

    @Test
    public void should_return_Flight_when_exist() throws IOException, NoEntityException {
        //given
        int searchedId = 1;
        //when
        when(flightServicesTest.getFlightById(searchedId)).thenReturn(flightModel);
    }

}