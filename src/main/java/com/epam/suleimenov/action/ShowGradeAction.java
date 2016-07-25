package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.CourseDAO;
import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.dao.StudentDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShowGradeAction implements Action {


    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
        private ActionResult gradeAction = new ActionResult("grade");

        @Override
        public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
            try(Connection connection = Service.getConnection()) {
                courseDAO = Service.getCourseDAO(connection);
                studentDAO = Service.getStudentDAO(connection);


            }catch (SQLException e) {
                e.printStackTrace();
            }


            int course_id = Integer.parseInt(req.getParameter("course_id"));
            Teacher teacher = (Teacher)req.getSession().getAttribute("teacher");
            Course course = courseDAO.getCourseById(course_id);

            ArrayList<Student> students = studentDAO.getStudentsByCourseId(course.getId());

            req.getSession().setAttribute("students", students);
            req.getSession().setAttribute("course", course);
           return  gradeAction;

        }


    }