package app.reservation.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReservationModel implements Serializable {
    private String id;
    private long flightId;
    private List<String> passengers;
}
