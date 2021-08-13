package app.model;

import lombok.Data;
import java.util.Date;

@Data
public class FlightModel {
    private int id;
    private Date date;
    private String destination;
    private int seats;
}
