package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.DAOFactory;
import com.epam.suleimenov.dao.GenericDAO;
import com.epam.suleimenov.dao.UserDAO;
import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.model.User;

import java.util.List;

/**
 * Created by Zhandos_Suleimenov on 8/1/2016.
 */
public class UserService {

    private DAOFactory daoFactory;
    private UserDAO userDAO;

    public UserService() {
        daoFactory = Service.getDAOFactory();
        userDAO = (UserDAO) daoFactory.createDAO(User.class);
    }

    public boolean clearUsers() {
        return userDAO.clear();
    }

    public User createUser(User user) {
        return userDAO.create(user);
    }

    public boolean deleteUser(User user) {
        return userDAO.delete(user.getId());
    }

    public User findUser(int user_id) {
        return userDAO.find(user_id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public User updateUser(User user) {
        return  userDAO.update(user);
    }

    public boolean checkUser(String login, String password, String userRole) {
       return userDAO.checkUser(login, password, userRole);
    }

    public User findUser(String email) {
        return  userDAO.findUserByEmail(email);
    }

}
