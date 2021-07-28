package app.reservation.controller;

import app.reservation.model.ReservationModel;
import app.reservation.services.ReservationService;
import app.user.model.UserModel;

import java.io.IOException;
import java.util.List;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController() throws IOException {
        reservationService = new ReservationService();
    }

    public ReservationModel reserve(List<UserModel> users, long flightId) {
        return reservationService.reserve(users, flightId);
    }

    public int countReservations(long flightId) throws IOException {
        return reservationService.countReservations(flightId);
    }

    public void delete(long id) throws IOException {
        reservationService.delete(id);
    }
}
