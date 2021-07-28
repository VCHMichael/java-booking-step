package app.user.model;

import app.reservation.model.ReservationModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserModel implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private List<ReservationModel> userReservations;
}
