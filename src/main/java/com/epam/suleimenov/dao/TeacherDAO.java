package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Teacher;
import java.util.ArrayList;

public interface TeacherDAO {

    void addTeacher(int teacher_id, int user_id);
    void clearTeachers();
    int findTeacherIdByUserId(int id);
    ArrayList<Teacher> getAllTeachers();
    Teacher getTeacherById(int id);
    int getTeacherIdByCourseId(int id);
    void removeTeacher(int id);

}
