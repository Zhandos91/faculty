package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Archive;
import java.util.ArrayList;

public interface ArchiveDAO {

    void addToArchive(int id, int student_id, int course_id, int grade);
    void clearArchive();
    ArrayList<Archive> getArchive();

}
