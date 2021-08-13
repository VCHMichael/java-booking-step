package app.dao;

import app.model.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class UserDao {
    private File userDB;

    public UserDao() throws IOException {
        userDB = new File("src/main/resources/user_db.json");
        userDB.createNewFile();
    }

    public UserModel create(UserModel userModel) throws IOException {
        List<UserModel> userDbList = findAll();
        userDbList.add(userModel);
        rewriteDB(userDbList);
        return userModel;
    }

    public UserModel findUserByFullName(String firstname, String lastname) throws IOException {
        List<UserModel> userDbList = findAll();
        return userDbList.stream()
                .filter(userModel -> userModel.getFirstName().equals(firstname) && userModel.getLastName().equals(lastname))
                .findFirst()
                .orElse(null);
    }

    public List<UserModel> findAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(userDB, new TypeReference<List<UserModel>>() {
        });
    }

    private void rewriteDB(List<UserModel> userModels) throws IOException {
        try (OutputStream fileOutputStream = new FileOutputStream(userDB)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String newDB = objectMapper.writeValueAsString(userModels);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write(newDB);
            writer.flush();
        }
    }
}
