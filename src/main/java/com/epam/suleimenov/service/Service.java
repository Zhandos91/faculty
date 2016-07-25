package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.dao.FacultyDAOImpl;
import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 7/4/2016.
 */
public class Service {

    private static FacultyDAO facultyDAO;

    public static FacultyDAO getFacultyDAO() {
        return  facultyDAO;
    }

    public void setFacultyDAO(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }
}
