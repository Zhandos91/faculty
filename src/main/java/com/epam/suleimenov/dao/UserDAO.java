package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.User;
import java.util.ArrayList;

public interface UserDAO {

    void addUser(User user);
    boolean checkUser(String login, String password, String userRole);
    void clearUsers();
    User findUserByEmail(String email);
    int findUserIdByTeacherId(int id);
    ArrayList<User> getAllUsers();
    User getUserById(int id);

}
