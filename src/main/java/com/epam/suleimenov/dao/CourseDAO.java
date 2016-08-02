package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;

import java.util.ArrayList;
import java.util.List;

public interface CourseDAO extends  GenericDAO<Course> {

    boolean addToCourseToUser(Course course, User user, String listener_type);

    boolean clearCourseToUser();

    List<Course> getAllCoursesByStatus(String status);

    List<Course> findCoursesByUserId(int user_id);

    List<User> findStudentsByCourseId(int id);

    List<User> findTeachersByCourseId(int id);

    List<User> findUsersByCourseId(int course_id, String listener_type);

}
