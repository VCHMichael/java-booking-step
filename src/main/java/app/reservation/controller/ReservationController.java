package app.reservation.controller;

import app.logger.Logger;
import app.reservation.model.ReservationModel;
import app.reservation.services.ReservationService;
import app.user.model.UserModel;
import app.user.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final Logger logger = new Logger(ReservationController.class);

    public ReservationController() throws IOException {
        reservationService = new ReservationService();
        userService = new UserService();
    }

    public ReservationModel reserve(List<UserModel> users, long flightId) {
        return reservationService.reserve(users, flightId);
    }

    public int countReservations(long flightId) throws IOException {
        return reservationService.countReservations(flightId);
    }

    public void cancel(String id) throws IOException {
        reservationService.delete(id);
    }

    public Map<String, Long> getUserReserves(String firstname, String lastname) {
        UserModel user;
        try {
            user = userService.findUserByFullName(firstname, lastname);
            if (user == null) return null;
            return reservationService.getUserReserves(user);
        } catch (IOException ioException) {
            logger.error("User or Reservation database not working, or system haven't enough rights", ioException);
            return null;
        }
    }
}
