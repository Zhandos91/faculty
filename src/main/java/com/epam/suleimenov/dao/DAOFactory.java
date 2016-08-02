package com.epam.suleimenov.dao;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(String whichFactory) {

        DAOFactory daoFactory;

        if (whichFactory.equalsIgnoreCase("facultyDAO")) {
            daoFactory = new FacultyDAOFactory();
            return daoFactory;
        } else {
            throw new DAOException("DAOFactory is not found!");
        }
    }

    public abstract <T> GenericDAO<T> createDAO(Class<T> myClass);

    public abstract void close();

}
