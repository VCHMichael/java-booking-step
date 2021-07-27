package app.reservation.model;

import app.user.model.UserModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ReservationModel implements Serializable {
    private long id;
    private String destination;
    private Date flightDate;
    private List<UserModel> passengers;

}
