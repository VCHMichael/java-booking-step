package app.user.model;

import app.reservation.model.ReservationModel;
import lombok.Data;

import java.util.List;

@Data
public class UserModel {
    private long id;
    private String name;
    private String surname;
    private List<ReservationModel> userReservations;
}
