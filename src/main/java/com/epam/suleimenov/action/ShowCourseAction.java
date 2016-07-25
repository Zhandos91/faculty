package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.CourseDAO;
import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.dao.StudentDAO;
import com.epam.suleimenov.dao.TeacherDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShowCourseAction implements Action {


    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private ActionResult courseInfo = new ActionResult("courseInfo");


    public ShowCourseAction() {
        try(Connection connection = Service.getConnection()) {
            courseDAO = Service.getCourseDAO(connection);
            studentDAO = Service.getStudentDAO(connection);
            teacherDAO = Service.getTeacherDAO(connection);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int course_id = Integer.parseInt(req.getParameter("course_id"));
        ArrayList<Student> students = studentDAO.getStudentsByCourseId(course_id);
        Course course = courseDAO.getCourseById(course_id);
        Teacher teacher = teacherDAO.getTeacherById(teacherDAO.getTeacherIdByCourseId(course_id));

        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("course", course);
        req.getSession().setAttribute("teacher", teacher);
        return courseInfo;

    }


}