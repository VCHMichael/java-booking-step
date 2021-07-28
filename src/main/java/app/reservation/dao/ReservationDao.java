package app.reservation.dao;

import app.reservation.model.ReservationModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDao {
    private File reservationDB;

    public ReservationDao() throws IOException {
        reservationDB = new File("src/main/resources/reservation_db.json");
        reservationDB.createNewFile();
    }

    public ReservationModel create(ReservationModel reservationModel) throws IOException {
        // todo id logic
        List<ReservationModel> reservationDbList = findAll();
        reservationDbList.add(reservationModel);
        rewriteDB(reservationDbList);
        return reservationModel;
    }

    public ReservationModel update(ReservationModel updatedReservationModel) throws IOException {
        List<ReservationModel> newReservations = findAll().stream()
                .map(reservationModel -> {
                    if (reservationModel.getId() == updatedReservationModel.getId()) {
                        return updatedReservationModel;
                    } else {
                        return reservationModel;
                    }
                })
                .collect(Collectors.toList());

        rewriteDB(newReservations);
        return updatedReservationModel;
    }

    public void delete(long id) throws IOException {
        List<ReservationModel> changedReservations = findAll().stream()
                .filter(reservationModel -> reservationModel.getId() != id)
                .collect(Collectors.toList());

        rewriteDB(changedReservations);
    }

    public int countReservations(long flightId) throws IOException {
        return findAll().stream()
                .filter(reservationModel -> reservationModel.getFlightId() == flightId)
                .findFirst()
                .map(r -> r.getPassengers().size())
                .orElse(0);
    }

    public ReservationModel findReservationByFlightId(long flightId) throws IOException {
       return findAll().stream()
               .filter(reservationModel -> reservationModel.getFlightId() == flightId)
               .findFirst()
               .orElse(null);
    }

    public List<ReservationModel> findAll() throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reservationDB, new TypeReference<List<ReservationModel>>(){});
    }

    private void rewriteDB(List<ReservationModel> reservationModelList) throws IOException {
        try (OutputStream fileOutputStream = new FileOutputStream(reservationDB)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String newDB = objectMapper.writeValueAsString(reservationModelList);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write(newDB);
            writer.flush();
        }
    }
}
