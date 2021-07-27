package app.reservation.dao;

import app.reservation.model.ReservationModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ReservationDao {
    private File database;

    public ReservationDao() throws IOException {
        database = new File("src/main/resources/database.json");
        database.createNewFile();
    }

    public void create(ReservationModel reservationModel) throws IOException {
        try (OutputStream fileOutputStream = new FileOutputStream(database)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String changedValue = objectMapper.writeValueAsString(reservationModel);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write(changedValue);
            writer.flush();
        }
    }

    public ReservationModel getById(long id) {
        return null;
    }
}
