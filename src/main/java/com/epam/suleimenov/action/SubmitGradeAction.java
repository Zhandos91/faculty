package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class SubmitGradeAction implements Action {



    private CourseDAO courseDAO;
    private ArchiveDAO archiveDAO;
    private TeacherDAO teacherDAO;
    private FacultyDAO facultyDAO;
    private ActionResult teacherAction = new ActionResult("teacher");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = Service.getConnection()) {
            courseDAO = Service.getCourseDAO(connection);
            archiveDAO = Service.getArchiveDAO(connection);
            teacherDAO = Service.getTeacherDAO(connection);
            facultyDAO = Service.getFacultyDAO(connection);

        Course course = (Course) req.getSession().getAttribute("course");
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        ArrayList<Student> students = (ArrayList<Student>) req.getSession().getAttribute("students");

        if (students != null && students.size() > 0) {
            for (Student student : students)
                archiveDAO.addToArchive(facultyDAO.getNextIdBySequence("archive"), student.getId(), course.getId(), Integer.parseInt(req.getParameter(Integer.toString(student.getId()))));

            courseDAO.changeCourseStatus(course.getId(), "archived");
            teacher = teacherDAO.getTeacherById(teacher.getId());
            req.getSession().setAttribute("teacher", teacher);

        }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherAction;

    }


}