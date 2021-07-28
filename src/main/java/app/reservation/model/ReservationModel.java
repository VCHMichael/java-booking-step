package app.reservation.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReservationModel implements Serializable {
    private long id;
    private long flightId;
    private List<Long> passengers;
}
