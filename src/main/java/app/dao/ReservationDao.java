package app.dao;

import app.model.ReservationModel;
import app.model.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReservationDao {
    private File reservationDB;

    public ReservationDao() {
        reservationDB =  new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("reservation_db.json")).toString());
    }

    public ReservationModel create(ReservationModel reservationModel) throws IOException {
        List<ReservationModel> reservationDbList = findAll();
        reservationDbList.add(reservationModel);
        rewriteDB(reservationDbList);
        return reservationModel;
    }

    public ReservationModel update(ReservationModel updatedReservationModel) throws IOException {
        List<ReservationModel> newReservations = findAll().stream()
                .map(reservationModel -> {
                    if (reservationModel.getId().equals(updatedReservationModel.getId())) {
                        return updatedReservationModel;
                    } else {
                        return reservationModel;
                    }
                })
                .collect(Collectors.toList());

        rewriteDB(newReservations);
        return updatedReservationModel;
    }

    public void delete(String id) throws IOException {
        List<ReservationModel> changedReservations = findAll().stream()
                .filter(reservationModel -> !reservationModel.getId().equals(id))
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

    public Map<String, Long> getUserReserves(UserModel user) throws IOException {
        return findAll().stream()
                .filter(reservationModel -> reservationModel.getPassengers().contains(user.getId()))
                .collect(Collectors.toMap(ReservationModel::getId, ReservationModel::getFlightId));
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
