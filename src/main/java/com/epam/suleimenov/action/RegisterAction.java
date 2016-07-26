package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.CourseDAO;
import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterAction implements Action {

    private ActionResult studentAction = new ActionResult("student");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String get_course_id = req.getParameter("course_id");
        if(get_course_id != null) {
            int course_id = Integer.parseInt(get_course_id);
            Student student = (Student) req.getSession().getAttribute("student");

            Service.getCourseDAO().matchCourseAndStudent(Service.getFacultyDAO().getNextIdBySequence("cs_match"), course_id, student.getId());
            student.getCourses().add(Service.getCourseDAO().getCourseById(course_id));
        }
        return studentAction;
    }
}
