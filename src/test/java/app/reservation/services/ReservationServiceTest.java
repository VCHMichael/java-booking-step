package app.reservation.services;

import app.dao.ReservationDao;
import app.model.ReservationModel;
import app.service.ReservationService;
import app.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class ReservationServiceTest {
    private ReservationService testInstance;
    private ReservationDao mockDao;

    @BeforeEach
    public void init() throws IOException {
        mockDao = mock(ReservationDao.class);
        testInstance = new ReservationService(mockDao);
        ReservationModel dummyModel = new ReservationModel();
        dummyModel.setId("1");
        dummyModel.setFlightId(2);
        dummyModel.setPassengers(Arrays.asList("4", "5", "100"));

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
        String id = "3";
        //when
        testInstance.delete(id);
        //then
        verify(mockDao, atLeast(1)).delete(anyString());
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
    
    @Test
    public void should_returnValidMap_when_existedUserReservationGiven() throws IOException {
        //given
        UserModel dummyUser = new UserModel();
        dummyUser.setFirstName("Serega");
        dummyUser.setLastName("Pupkin");
        dummyUser.setId("100");
        HashMap<String, Long> searchResult = new HashMap<>();
        searchResult.put("1", 1L);
        when(mockDao.getUserReserves(dummyUser))
                .thenReturn(searchResult);
        Map<String, Long> expect = new HashMap<>();
        expect.put("1", 1L);
        //when
        Map<String, Long> result = mockDao.getUserReserves(dummyUser);
        //then
        Assertions.assertEquals(expect, result);
    }
}