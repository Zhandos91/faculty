package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;

import java.util.ArrayList;

public interface FacultyDAO extends  ArchiveDAO, CourseDAO, StudentDAO, TeacherDAO, UserDAO {
    int getNextIdBySequence(String sequence);
}
