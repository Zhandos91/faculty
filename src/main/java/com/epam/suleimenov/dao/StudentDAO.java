package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Student;
import java.util.ArrayList;

public interface StudentDAO {

    void addStudent(int student_id, int user_id);
    void clearStudents();
    int findStudentIdByUserId(int id);
    ArrayList<Student> getAllStudents();
    Student getStudentById(int id);
    ArrayList<Student> getStudentsByCourseId(int id);
    void removeStudent(int id);

}
