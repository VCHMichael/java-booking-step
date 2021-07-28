package app;

import app.reservation.dao.ReservationDao;
import app.reservation.model.ReservationModel;

import java.io.IOException;
import java.util.UUID;

public class Main {
  public static void main(String[] args) throws IOException {

    ReservationModel model = new ReservationModel();
    ReservationDao dao = new ReservationDao();

    System.out.println(dao.findAll());

    dao.create(model);
    System.out.println(dao.findAll());


//   String uuid = UUID.randomUUID().toString();
//    System.out.println(uuid);
  }
}
