package app.flight.model;

import lombok.Data;
import java.util.Date;

@Data
public class Flight {
    private int id;
    private Date date;
    private String destination;
    private int seats;
}
