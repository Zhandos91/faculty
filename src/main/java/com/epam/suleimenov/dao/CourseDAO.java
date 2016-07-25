package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Course;
import java.util.ArrayList;

public interface CourseDAO {

    void addCourse(Course course);
    void changeCourseStatus(int id, String status);
    void clearCourses();
    void clearCourseToStudent();
    ArrayList<Course> getAllCourses();
    ArrayList<Course> getAllCoursesByStatus(String status);
    Course getCourseById(int id);
    ArrayList<Course> getCoursesByStudentId(int id);
    void matchCourseAndStudent(int id, int course_id, int student_id);
    void removeCourse(int id);

}
