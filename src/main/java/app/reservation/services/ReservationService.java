package app.reservation.services;

import app.reservation.dao.ReservationDao;
import app.reservation.model.ReservationModel;
import app.user.model.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService() throws IOException {
        reservationDao = new ReservationDao();
    }

    public ReservationModel reserve(List<UserModel> users, long flightId) {
        List<Long> passengers = users.stream()
                .map(UserModel::getId)
                .collect(Collectors.toList());

        try {
            ReservationModel reservationFromDB = reservationDao.findReservationByFlightId(flightId);
            if (reservationFromDB != null) {
                reservationFromDB.getPassengers().addAll(passengers);
                return reservationDao.update(reservationFromDB);
            } else {
                ReservationModel newReservation = new ReservationModel();
                newReservation.setFlightId(flightId);
                newReservation.setPassengers(passengers);
                // todo id logic
                newReservation.setId(1);
                return reservationDao.create(newReservation);
            }

        } catch (IOException ioException) {
            return null;
        }
    }

    public int countReservations(long flightId) throws IOException {
        return reservationDao.countReservations(flightId);
    }

    public void delete(long id) throws IOException {
        reservationDao.delete(id);
    }
}
