package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FacultyDAO {
    int getNextIdBySequence(String sequence);
}
