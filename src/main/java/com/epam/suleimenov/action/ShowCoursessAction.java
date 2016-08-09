package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCoursessAction implements Action {

    private ActionResult registerAction = new ActionResult("registration");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try (CourseService courseService = new CourseService()) {
            User student = (User) req.getSession().getAttribute("student");
            List<Course> student_courses = student.getCourses();
            List<Course> registration_courses = courseService.getAllCoursesByStatus("open");

            for (Course course : student_courses)
                registration_courses.remove(course);

            req.getSession().setAttribute("courses", registration_courses);
        }

        return registerAction;
    }
}

