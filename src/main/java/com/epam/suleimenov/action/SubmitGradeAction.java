package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class SubmitGradeAction implements Action {


    private FacultyDAO facultyDAO = null;
    private ActionResult teacherAction = new ActionResult("teacher");


    public SubmitGradeAction() {
        facultyDAO = Service.getFacultyDAO();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Course course = (Course) req.getSession().getAttribute("course");
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        ArrayList<Student> students = (ArrayList<Student>) req.getSession().getAttribute("students");

        if (students != null && students.size() > 0) {
            for (Student student : students)
                facultyDAO.addToArchive(facultyDAO.getNextIdBySequence("archive"), student.getId(), course.getId(), Integer.parseInt(req.getParameter(Integer.toString(student.getId()))));

            facultyDAO.changeCourseStatus(course.getId(), "archived");
            teacher = facultyDAO.getTeacherById(teacher.getId());
            req.getSession().setAttribute("teacher", teacher);

        }
        return teacherAction;

    }


}