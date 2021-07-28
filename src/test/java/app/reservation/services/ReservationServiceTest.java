package app.reservation.services;

import app.reservation.dao.ReservationDao;
import app.reservation.model.ReservationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class ReservationServiceTest {
    private ReservationService testInstance;
    private ReservationDao mockDao;

    @BeforeEach
    public void init() throws IOException {
        mockDao = mock(ReservationDao.class);
        testInstance = new ReservationService(mockDao);
        ReservationModel dummyModel = new ReservationModel();
        dummyModel.setId(1);
        dummyModel.setFlightId(2);
        dummyModel.setPassengers(Arrays.asList(4L, 5L, 6L));

        when(mockDao.findReservationByFlightId(2))
                .thenReturn(dummyModel);
        when(mockDao.update(any(ReservationModel.class)))
                .thenReturn(dummyModel);
        when(mockDao.create(any(ReservationModel.class)))
                .thenReturn(dummyModel);
        when(mockDao.countReservations(dummyModel.getFlightId()))
                .thenReturn(dummyModel.getPassengers().size());
    }

    @Test
    public void should_callUpdate_when_reservationExist() throws IOException {
        //given
        long flightId = 2;
        //when
        testInstance.reserve(new ArrayList<>(), flightId);
        //then
        verify(mockDao, atLeast(1)).update(any());
    }

    @Test
    public void should_callCreate_when_reservationNotExist() throws IOException {
        //given
        long flightId = 3;
        //when
        testInstance.reserve(new ArrayList<>(), flightId);
        //then
        verify(mockDao, atLeast(1)).create(any());
    }

    @Test
    public void should_callDelete_when_reservationExist() throws IOException {
        //given
        long flightId = 3;
        //when
        testInstance.delete(flightId);
        //then
        verify(mockDao, atLeast(1)).delete(anyLong());
    }

    @Test
    public void should_returnReservationSize_when_reservationExist() throws IOException {
        //given
        long flightId = 2;
        //when
        long reservationSize = testInstance.countReservations(flightId);
        //then
        Assertions.assertEquals(3, reservationSize);
    }
}