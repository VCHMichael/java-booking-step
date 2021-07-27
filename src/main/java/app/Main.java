package app;

import app.reservation.dao.ReservationDao;
import app.reservation.model.ReservationModel;

import java.io.IOException;
import java.util.Date;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Hello World");

    ReservationModel model = ReservationModel.builder()
            .id(1)
            .destination("Urugvay")
            .flightDate(new Date())
            .build();

    ReservationDao dao = new ReservationDao();
    dao.create(model);
  }

}
