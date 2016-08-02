package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.User;

public interface UserDAO extends GenericDAO<User> {

    boolean checkUser(String login, String password, String userRole);

    User findUserByEmail(String email);
}
