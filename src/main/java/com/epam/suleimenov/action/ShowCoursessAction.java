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
import java.util.ArrayList;

/**
 * Created by Zhandos_Suleimenov on 7/21/2016.
 */
public class ShowCoursessAction implements Action {

    private ActionResult registerAction = new ActionResult("registration");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Student student = (Student) req.getSession().getAttribute("student");
        ArrayList<Course> student_courses = student.getCourses();
        ArrayList<Course> registration_courses = Service.getCourseDAO().getAllCoursesByStatus("open");

        for (Course course : student_courses)
            registration_courses.remove(course);

        req.getSession().setAttribute("courses", registration_courses);

        return registerAction;
    }
}

