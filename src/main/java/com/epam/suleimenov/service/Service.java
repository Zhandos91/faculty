package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.DAOFactory;
import com.epam.suleimenov.util.Utils;

public class Service implements  AutoCloseable {

    protected DAOFactory daoFactory;
    private final String FACULTY_DAO_FACTORY = "faculty_DAO";
    private final String DAO_PROPERTY = "dao.properties";

    public Service() {
            daoFactory = DAOFactory.getDAOFactory(Utils.getFile(DAO_PROPERTY).getProperty(FACULTY_DAO_FACTORY));
    }

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }
}
