package app.flight.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Flight {
    private final int id;
    private LocalDate date;
    private String destination;
    private int seats;
}
