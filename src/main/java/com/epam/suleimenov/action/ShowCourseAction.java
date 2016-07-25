package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class ShowCourseAction implements Action {


    private FacultyDAO facultyDAO = null;
    private ActionResult courseInfo = new ActionResult("courseInfo");


    public ShowCourseAction() {
        facultyDAO = Service.getFacultyDAO();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int course_id = Integer.parseInt(req.getParameter("course_id"));
        ArrayList<Student> students = facultyDAO.getStudentsByCourseId(course_id);
        Course course = facultyDAO.getCourseById(course_id);
        Teacher teacher = facultyDAO.getTeacherById(facultyDAO.getTeacherIdByCourseId(course_id));

        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("course", course);
        req.getSession().setAttribute("teacher", teacher);
        return courseInfo;

    }


}