package app.user.services;
import app.user.dao.UserDao;
import app.user.model.UserModel;

import java.io.IOException;
import java.util.UUID;

public class UserService {
    private  UserDao userDao;

    public UserService() throws IOException {
        userDao = new UserDao();
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserModel create(String firstname, String lastname) throws IOException {
        UserModel newUser = new UserModel();
        String id = UUID.randomUUID().toString();
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);
        newUser.setId(id);
        return userDao.create(newUser);
    }

    public UserModel findUserByFullName(String firstname, String lastname) throws IOException {
        return userDao.findUserByFullName(firstname, lastname);
    }
}
