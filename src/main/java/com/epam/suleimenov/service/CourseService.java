package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.CourseDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;

import java.util.List;

public class CourseService extends Service {

    private CourseDAO courseDAO;

    public CourseService() {
        courseDAO = (CourseDAO) daoFactory.createDAO(Course.class);
    }

    public boolean clearCourses() {
        return courseDAO.clear();
    }

    public Course createCourse(Course course) {
        return courseDAO.create(course);
    }

    public boolean deleteCourse(int id) {
        return courseDAO.delete(id);
    }

    public Course findCourse(int id) {
        return courseDAO.find(id);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public Course updateCourse(Course course) {
        return  courseDAO.update(course);
    }

    public boolean addCourseToUser(Course course, User user, String listener_type) {
        return courseDAO.addToCourseToUser(course, user, listener_type);
    }

    public boolean clearCourseToUser() {
        return courseDAO.clearCourseToUser();
    }

    public List<Course> getAllCoursesByStatus(String status) {
        return courseDAO.getAllCoursesByStatus(status);
    }

    public List<Course> findCoursesByUser(User user) {
        return courseDAO.findCoursesByUserId(user.getId());
    }

    public List<User> findStudentsByCourse(Course course){
        return courseDAO.findStudentsByCourseId(course.getId());
    }

    public List<User> findTeachersByCourse(Course course) {
        return courseDAO.findTeachersByCourseId(course.getId());
    }

}
