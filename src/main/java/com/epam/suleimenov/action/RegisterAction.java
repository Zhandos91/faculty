package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhandos_Suleimenov on 7/21/2016.
 */
public class RegisterAction implements Action {

    private FacultyDAO facultyDAO = null;
    private ActionResult studentAction = new ActionResult("student");

    public RegisterAction() {
        facultyDAO = Service.getFacultyDAO();

    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int course_id = Integer.parseInt(req.getParameter("course_id"));
        Student student = (Student) req.getSession().getAttribute("student");

        facultyDAO.matchCourseAndStudent(facultyDAO.getNextIdBySequence("cs_match"), course_id, student.getId());
        student.getCourses().add(facultyDAO.getCourseById(course_id));
        return studentAction;
    }
}
