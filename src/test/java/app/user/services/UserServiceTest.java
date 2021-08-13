package app.user.services;

import app.dao.UserDao;
import app.model.UserModel;
import app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserDao mockDao;
    private UserService testService;

    @BeforeEach
    public void init() throws IOException {
        mockDao = mock(UserDao.class);
        testService = new UserService(mockDao);
        UserModel userModel = new UserModel();
        userModel.setId("1");
        userModel.setFirstName("Vasia");
        userModel.setLastName("Pupkin");

        when(mockDao.create(userModel))
                .thenReturn(userModel);
        when(mockDao.findUserByFullName("Vasia", "Pupkin"))
                .thenReturn(userModel);
    }

    @Test
    public void should_createUser_when_validDataGiven() throws IOException {
        //given
        //when
        testService.create("Vasia", "Pupkin");
        //then
        verify(mockDao, atLeast(1)).create(any());
    }

    @Test
    public void should_returnUser_when_userInDatabase() throws IOException {
        //given
        String userFirstname = "Vasia";
        String userLastname = "Pupkin";
        UserModel userModel = new UserModel();
        userModel.setId("1");
        userModel.setFirstName(userFirstname);
        userModel.setLastName(userLastname);
        //when
        UserModel result = testService.findUserByFullName(userFirstname, userLastname);
        //then
        Assertions.assertEquals(userModel, result);
    }
}