package app.user.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModel implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
}
